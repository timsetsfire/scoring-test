
import scala.collection.JavaConverters.mapAsJavaMap
import com.datarobot.prediction.{Predictors, IPredictorInfo, IRegressionPredictor, IClassificationPredictor}
import scala.io.Source




object Main extends App {

    def scoreOneWithExplanations(model: IPredictorInfo, datesetPath: String) { 
        val data = Source.fromFile(datesetPath).getLines
        val headers = data.next.split(",").map(_.trim.replace("\"", ""))
        val firstRow = data.next.split(",").map(_.trim.replace("\"", ""))
        val sMap = (headers zip firstRow).toMap
        val features = mapAsJavaMap(sMap)
        val defaultExplanationParams = model.getDefaultPredictionExplanationParams
        val explanationParams = defaultExplanationParams.withMaxCodes(3).withThresholdHigh(0.6).withThresholdLow(0.2)
       try { 
            val predWithExplanations = model.getPredictorClass.getName match { 
                case "com.datarobot.prediction.IClassificationPredictor" => model.asInstanceOf[IClassificationPredictor].scoreWithExplanations(features, explanationParams)
                case "com.datarobot.prediction.IRegressionPredictor" => model.asInstanceOf[IRegressionPredictor].scoreWithExplanations(features, explanationParams)
                case _ => throw new Exception("model type not supported")
            }
            println(predWithExplanations)
            println(predWithExplanations.getClass.getName)
            predWithExplanations
        } catch { 
            case e: Exception => println(e)
        }
    } 

    val influencers = "data/influencers_full_20.csv"
    val boston = "data/BostonPublicRaises_80.csv"

    val models = Predictors.getAllPredictors 

    while(models.hasNext) { 
        val model = models.next
        if(model.getModelId == "660e2a3080453d58600f3252") { 
            println("classifying first record of influencers dataset with explainations")
            scoreOneWithExplanations(model, influencers)
            } else { 
            println("predicting first record of raise dataset with explainations")
            scoreOneWithExplanations(model, boston)
        }
    }






}