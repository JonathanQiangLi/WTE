package vital.training.spark.tutorial

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.rdd.RDD

object TutorialOne {
  def main(args: Array[String]) = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val ss = initSpark()
  }

  def initSpark(): SparkSession = {
    val conf = new SparkConf()
      .setMaster("local[4]")
      .setAppName("tutorialone")
    SparkSession.builder().config(conf).getOrCreate()
  }

  def printRDD(rdd:RDD[_], count:Int) = {
    println(rdd.take(count).mkString(","))
  }

  def genRDDOldWay(ss:SparkSession) : RDD[Int] = {
    ss.sparkContext.parallelize(Seq(1,2,3,4,5,6,7,8));
  }

  def genRDDTupleOldWay(ss:SparkSession) : RDD[(Int, String)] = {
    import ss.implicits._
    ss.sparkContext.parallelize(Seq(
      (1, "one"), (2, "two"),(3, "three"),
      (4, "four"),(5, "five"),(6, "six"),
      (7, "seven"),(8, "eight")))
  }

  def genRange(ss:SparkSession) : RDD[Int] = {
    ss.sparkContext.parallelize(1 to 10)
  }

  def genDataFrame(ss:SparkSession): DataFrame = {
    import ss.implicits._
    Seq((20000, "CA", "Honda", "Accord", 5, 30000, 100000),
      (10000, "CA", "Honda", "Civic", 4, 18000, 90000),
      (20000, "NY", "Mercedes", "E300", 8, 45000, 140000),
      (15000, "NY", "Honda", "Accord", 5, 29000, 110000),
      (7500, "NY", "Honda", "Civic", 5, 16000, 105000),
      (30000, "CA", "Mercedes", "E300", 7, 46500, 120000)).toDF("price", "state", "make", "model", "age", "msrp", "mileage")
  }
  
  /**
    * Generate a dataframe that has two columns: number and square that has totalRows of rows.
    *
    *  number is from 1 to totalRows
    *
    *  number square
    *  1      1
    *  2      4
    *  3      9
    *
    *  id (increasing integers)
    *  square (squre of the id)
    *
    * @param totalRows
    * @return
    */

//  def genDataFrameExercise(ss:SparkSession, totalRows:Int): DataFrame = {
//  }

}
