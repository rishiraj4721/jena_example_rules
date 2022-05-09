package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.cache.Resource;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.rulesys.FBRuleReasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.XSD;
// import org.apache.jena.rdf.model.RDFWriter;

import com.github.andrewoma.dexx.collection.ArrayList;
import com.github.andrewoma.dexx.collection.IndexedList;
import com.sun.tools.javac.code.Attribute.Enum;

/**
 * Hello world!
 *
 */
public class App {
	static final String inputFileName = "nutritionontology.owl";
	static Property hasGestationalAgeAtBirth;
	static Property hasFeedIntolerance;
	static Property hasDayOfLife;
	static Property hasFeedingAdvancement;
	static Property hasFeedingVolume;
	static Property hasRiskFactor;
	static Property givenFeedVolume;//
	static Property hasBaseDeficitValue;//
	static Property hasCordGaspHValue; //
	static Property hasMonoTwinStatus;//
	static Property pressorGiven;//
	static Property hasSkinDiscoloration;//
	static Property hasGastricAspirate;//
	static Property hasXRayAbdominStatus;//
	static Property hasGastricAspirateAbnormalColor;//
	static String confquery1;
	static Individual ins;
	static Individual unique_1;
	static Individual unique2;
	static Individual uniquevall;

	static long day[];

	@SuppressWarnings("deprecation")

	public static void main(String[] args) throws FileNotFoundException {

		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM, null);
		// use the FileManager to find the input file
		InputStream in = new FileInputStream(inputFileName);
		model.read(in, "");
		String med = "http://www.childhealthimprints.com/SampleOntology/Testing/";
		org.apache.jena.rdf.model.Resource r = model.getResource(med);

		hasDayOfLife = model.getProperty(med, "hasDayOfLife");
		hasFeedIntolerance = model.getProperty(med, "hasFeedIntolerance");
		hasGestationalAgeAtBirth = model.getProperty(med, "hasGestationalAgeAtBirth");
		hasFeedingVolume = model.getProperty(med, "hasFeedingVolume");
		hasFeedingAdvancement = model.getProperty(med, "hasFeedingAdvancement");
		hasRiskFactor = model.getProperty(med, "hasRiskFactor");
		givenFeedVolume = model.getProperty(med, "givenFeedVolume");
		Property hasBabyID = model.getProperty(med, "hasBabyID");
		hasMonoTwinStatus = model.getProperty(med, "hasMonoTwinStatus"); //
		hasCordGaspHValue = model.getProperty(med, "hasCordGaspHValue"); //
		hasBaseDeficitValue = model.getProperty(med, "hasBaseDeficitValue"); //
		Property hasChestCompressionDuration = model.getProperty(med, "hasChestCompressionDuration"); //
		pressorGiven = model.getProperty(med, "pressorGiven"); //
		Property hasVomitColor = model.getProperty(med, "hasVomitColor"); //
		Property hasVomitVolume = model.getProperty(med, "hasVomit"); //

		Property hasAbdominalDistension = model.getProperty(med, "hasAbdominalDistension");
		Property hasSkinDiscoloration = model.getProperty(med, "hasSkinDiscoloration");
		Property hasAbdominalTenderness = model.getProperty(med, "hasAbdominalTenderness");
		Property hasVisibleBowelLoop = model.getProperty(med, "hasVisibleBowelLoop");
		Property bloodPresentInStool = model.getProperty(med, "bloodPresentInStool");
		Property hasMetabolicAcidosis = model.getProperty(med, "hasMetabolicAcidosis");
		Property hasApnea = model.getProperty(med, "hasApnea");
		Property hasRespiratoryDistress = model.getProperty(med, "hasRespiratoryDistress");
		Property hasLethargy = model.getProperty(med, "hasLethargy");
		Property hasTemperatureInStability = model.getProperty(med, "hasTemperatureInStability");
		Property haslowPlateletCount = model.getProperty(med, "haslowPlateletCount");
		Property hasGastricAspirate = model.getProperty(med, "hasGastricAspirate");

		Property hasXRayAbdominStatus = model.getProperty(med, "hasX-RayAbdominStatus");
		Property hasGastricAspirateAbnormalColor = model.getProperty(med, "hasGastricAspirateAbnormalColor");
		Property hasLongCapillaryRefillTime = model.getProperty(med, "hasLongCapillaryRefillTime");
		Property hasCentralPeripheralDifference = model.getProperty(med, "hasCentralPeripheralDifference");
		Property hasTechycardia = model.getProperty(med, "hasTechycardia");
		Property hasBloodPressure = model.getProperty(med, "hasBloodPressure");

		Property hasLowBloodPressure = model.getProperty(med, "hasLowBloodPressure");
		Property hasColdPeripheries = model.getProperty(med, "hasColdPeripheries");
		Property hasLactateLevel = model.getProperty(med, "hasLactateLevel");
		Property hasNecrotizingEnterocolitis = model.getProperty(med, "hasNecrotizingEnterocolitis");
		Property hasUniqueID = model.getProperty(med, "hasUniqueID");

		// ****************************************************************
		Date datetime1 = null;
		Date doatoa = null;

		Connection con = null;

		Statement stmt = null;
		Statement stmt1 = null;

		java.sql.ResultSet rec1 = null;
		java.sql.ResultSet rec2 = null;
		java.sql.ResultSet rec4 = null;//
		java.sql.ResultSet rec5 = null;//
		java.sql.ResultSet rec6 = null;//
		java.sql.ResultSet rec7 = null;//
		java.sql.ResultSet rec8 = null;//
		java.sql.ResultSet rec9 = null;//
		java.sql.ResultSet rec10 = null;//
		java.sql.ResultSet rec11 = null;//
		java.sql.ResultSet rec12 = null;//
		java.sql.ResultSet rec13 = null;//
		java.sql.ResultSet rec14 = null;//
		java.sql.ResultSet rec15 = null;//
		java.sql.ResultSet rec16 = null;//
		java.sql.ResultSet rec17 = null;//
		java.sql.ResultSet rec18 = null;//

		java.sql.ResultSet rec19 = null;//
		java.sql.ResultSet rec20 = null;//
		java.sql.ResultSet rec21 = null;//
		java.sql.ResultSet rec22 = null;//
		java.sql.ResultSet rec23 = null;//
		java.sql.ResultSet rec24 = null;//
		java.sql.ResultSet rec25 = null;//

		java.sql.ResultSet uhidrec = null;

		ResultSet rec3 = null;

		int dayoflife = 0; // this is a field
		Date entrydatetime = null;
		Date dobandtime = null;
		String gestationalweek = null;
		String totalfeed_ml_per_kg_day;
		Date dateofadmission;
		String timeofadmission;

		Double workingweight = (double) 1;
		Dictionary geek = new Hashtable();
		String extnum;
		String[] nextLine;
		String ph = null; //
		String be = null; //
		String chest_comp_time = null; //
		String word = "";

		try {

			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chil", "postgres", "hellothere");
			stmt = con.createStatement();

			String getUHID = "select uhid from apollo.baby_detail";
			PreparedStatement ps = con.prepareStatement(getUHID);

			uhidrec = ps.executeQuery();
			// String query1result = "query.txt";

			int uniqueval = 0;

			// **********************config
			// file***************************************************************
			// Properties prop = new Properties();
			// InputStream input = null;
			// String filePathPropFile = "config.properties";
			// try {
			// input = App.class.getClassLoader().getResourceAsStream(filePathPropFile);
			// prop.load(input);
			// String confquery = prop.getProperty("query1");//like userName
			// String conf = prop.getProperty("query4");//like userName
			// //System.out.println("..............................................................................................................");

			// //
			// System.out.println(confquery+"......uhid....................................................................................");
			// //System.out.println(conf+"......uhid....................................................................................");

			// } catch (IOException ex) {
			// ex.printStackTrace();
			// }

			// String result = "Dr. Raghava Mutharaju";
			/*
			 * //**********************config
			 * file***************************************************************
			 * //**********************config
			 * file***************************************************************
			 * Properties prop1 = new Properties();
			 * InputStream input1 = null;
			 * String filePathPropFile1 = "sparqlquery.conf";
			 * try {
			 * input1 = App.class.getClassLoader().getResourceAsStream(filePathPropFile1);
			 * prop1.load(input1);
			 * confquery1 = prop1.getProperty("query1");//like userName
			 * //System.out.println(confquery1+
			 * ".............................................................................................................."
			 * );
			 * 
			 * String[] words=confquery1.split("___");//splits the string based on
			 * whitespace
			 * for(String w:words){
			 * // System.out.println(w);
			 * System.out.println(w.valueOf(w));
			 * result=result+(w) ;
			 * }
			 * System.out.println("hello"+
			 * ".............................................................................................................."
			 * );
			 * 
			 * System.out.println(conversion("query1",prop1));
			 * 
			 * System.out.println("hello"+
			 * ".............................................................................................................."
			 * );
			 * // System.out.println(confquery1+
			 * ".............................................................................................................."
			 * );
			 * 
			 * // System.out.println(confquery+
			 * "......uhid...................................................................................."
			 * );
			 * //System.out.println(conf+
			 * "......uhid...................................................................................."
			 * );
			 * 
			 * 
			 * } catch (IOException ex) {
			 * ex.printStackTrace();
			 * }
			 * 
			 */

			// ****************config file
			// *******************************************************************

			while (uhidrec.next()) {
				uniqueval = uniqueval + 1;

				// String uhid="RSHI.0000027879";

				String uhid = uhidrec.getString("uhid");
				uniquevall = (Individual) model.createIndividual(med + uhid,
						model.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));

				model.addLiteral(uniquevall, hasUniqueID, uniqueval);

				System.out.println(uhid + "...uhid...");

				String query1 = "select  dateofadmission,timeofadmission,timeofbirth, dateofbirth, gestationweekbylmp, gestationdaysbylmp from apollo.baby_detail where uhid"
						+ "=" + "'" + uhid + "'";

				// PreparedStatement ps1 = con.prepareStatement(result);
				Statement ps1 = con.createStatement();
				rec1 = ps1.executeQuery(query1);

				while (rec1.next()) {
					System.out.println(rec1.getString("dateofadmission") + " " + rec1.getString("timeofadmission") + " "
							+ rec1.getString("timeofbirth") + " " + rec1.getString("dateofbirth") + " "
							+ rec1.getString("gestationweekbylmp") + " " + rec1.getString("gestationdaysbylmp"));

					String time1 = rec1.getString("timeofbirth");
					gestationalweek = rec1.getString("gestationweekbylmp");
					String replacetime = time1.replaceFirst(",", ":");
					String reptime = replacetime.replaceFirst(",", " ");
					dateofadmission = rec1.getDate("dateofadmission");
					timeofadmission = rec1.getString("timeofadmission");

					System.out.println("..gestational.." + gestationalweek);

					Date dob = rec1.getDate("dateofbirth");

					System.out.println("....datetime..");

					SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
					SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
					Date date = (Date) parseFormat.parse(reptime);
					// System.out.println(parseFormat.format(date) + " = " +
					// displayFormat.format(date));

					String startingDate = new SimpleDateFormat("yyyy-MM-dd").format((dob));

					String startingTime = new SimpleDateFormat("hh:mm:ss").format(date);

					String DateTime = startingDate + " " + startingTime;
					System.out.println(DateTime + "....datetime..");

					SimpleDateFormat parseFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					datetime1 = (Date) parseFormat1.parse(DateTime);
					System.out.println(datetime1 + "....doame..");
					// ******************************************************

					String replacetoa = timeofadmission.replaceFirst(",", ":");
					String reptime_toa = replacetoa.replaceFirst(",", " ");
					System.out.println(reptime_toa + "....time..");
					System.out.println(dateofadmission + "......doa.....");

					String startingDate1 = new SimpleDateFormat("yyyy-MM-dd").format((dateofadmission));
					Date date1 = (Date) parseFormat.parse(reptime_toa);

					String startingTime1 = new SimpleDateFormat("hh:mm:ss").format(date1);

					String DateTime1 = startingDate1 + " " + startingTime1;

					SimpleDateFormat parseFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					doatoa = (Date) parseFormat2.parse(DateTime1); // dateofadmission and time of admission
					System.out.println(doatoa + "....doame..");
				}

				// *********calculate DOL for current date

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date newdate = new Date();
				String entry = formatter.format(newdate);
				// System.out.println(formatter.format(newdate));

				Date datetime2 = (Date) formatter.parse(entry);

				long currentdiff = datetime2.getTime() - datetime1.getTime();
				long doa_diff = doatoa.getTime() - datetime1.getTime();
				int doa_DOL = (int) TimeUnit.MILLISECONDS.toDays(doa_diff);

				System.out.println((doa_DOL) + "doacurrent");

				int currentDOL = (int) TimeUnit.MILLISECONDS.toDays(currentdiff);
				System.out.println((currentDOL) + "currentd");

				// *************************************************************************************************************

				String query4 = "select  uhid,chest_comp_time from apollo.birth_to_nicu WHERE uhid=" + "'" + uhid + "'";
				String chestcomp = null;
				// Risk factor
				Statement stmt4 = con.createStatement();
				{
					rec4 = stmt4.executeQuery(query4);
					while (rec4.next())
						chestcomp = rec4.getString("chest_comp_time");
					System.out.println("be...");
				}

				// **********************************************************************************************************************

				String query5 = " Select ph,be,uhid from apollo.nursing_bloodgas WHERE uhid=" + "'" + uhid + "'"
						+ "AND be is NOT NULL";
				Statement stmt5 = con.createStatement();
				rec5 = stmt5.executeQuery(query5);
				try {
					while (rec5.next()) {
						ph = rec5.getString("ph");
						be = rec5.getString("be");
						System.out.println("be..." + ph);
						System.out.println("be..." + be);
					}
				}

				catch (Exception e) {
					System.out.println(e);
				}

				// ***************************************************************************************************************
				String babytype = null;
				boolean twin_status = false;
				String query6 = " select baby_type,uhid from apollo.baby_detail WHERE uhid=" + "'" + uhid + "'";
				Statement stmt6 = con.createStatement();
				rec6 = stmt6.executeQuery(query6);
				while (rec5.next()) {
					babytype = rec6.getString("baby_type");
					if (babytype.equalsIgnoreCase("Twins") || babytype.equalsIgnoreCase("Triplets")) {
						twin_status = true;
					} else {
						twin_status = false;
					}

				}

				// **************************************************************************

				// ***************************************************************************************************************
				String pressor = null;
				Boolean val = null;

				String query7 = " Select medicationtype,uhid from apollo.baby_prescription WHERE uhid=" + "'" + uhid
						+ "'";
				Statement stmt7 = con.createStatement();
				rec7 = stmt7.executeQuery(query7);
				int i2 = 0;
				while (rec7.next()) {
					pressor = rec7.getString("medicationtype");
					if (pressor == "TYPE0004") {
						val = true;
					} else {
						val = false;
					}
				}

				// **************************************************************************

				Individual unique1;
				Date entry_timestamp;
				String vomit_color;

				String vomit_DOL = null;

				String query8 = " select  vomit,entry_timestamp,vomit_color from apollo.nursing_intake_output WHERE uhid="
						+ "'" + uhid + "'";

				Statement stmt8 = con.createStatement();
				rec8 = stmt8.executeQuery(query8);
				while (rec8.next()) {
					entry_timestamp = rec8.getDate("entry_timestamp");
					String vomit = rec8.getString("vomit");
					System.out.println(entry_timestamp + "entryyy");
					vomit_color = rec8.getString("vomit_color");
					long cudiff = datetime2.getTime() - entry_timestamp.getTime();
					int nursingtime = (int) TimeUnit.MILLISECONDS.toDays(cudiff);
					System.out.println(nursingtime + "nursing time");
					System.out.println(vomit_color + "vomit color nursing time");

					// if(model.getIndividual(med+uhid+"_"+nursingtime) == null){

					// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+nursingtime);
					// }

					// else {
					unique_1 = (Individual) model.createIndividual(med + uhid + "_" + nursingtime,
							model.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
					System.out.println("unique8" + unique_1);

					// }
					if (vomit_color != null) {
						if ((vomit_color == "blood stained") || (vomit_color == "bloody")) {
							model.addLiteral(unique_1, hasVomitColor, ResourceFactory.createTypedLiteral(vomit_color));
						}
					}
					if (vomit != null) {
						model.addLiteral(unique_1, hasVomitVolume, ResourceFactory.createTypedLiteral(vomit));
					}
					// System.out.println((nursingtime)+ vomit_DOL+"nursing time doa");
				}

				// **************************************************************************************************************************************
				// **input ontology and define properties

				String query9 = "select uhid,assessment_time, abdominal_signs from apollo.sa_feed_intolerance    WHERE uhid="
						+ "'" + uhid + "'" + "AND abdominal_signs is not null order by assessment_time";
				Statement stmt9 = con.createStatement();
				rec9 = stmt9.executeQuery(query9);
				while (rec9.next()) {
					Date assessment_time = rec9.getDate("assessment_time");

					String abdominSign = rec9.getString("abdominal_signs");
					System.out.println(abdominSign + "abdomin");

					// System.out.println(abdominSign+"abdentryyy");
					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					// System.out.println(assesstime+"assess time");
					// System.out.println("9");

					Individual unique_2 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {

						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique9"+unique_1);

					}

					try {
						if (abdominSign.contains("Erythema")) {
							System.out.println("yessss");
							model.addLiteral(unique_1, hasAbdominalTenderness,
									ResourceFactory.createTypedLiteral("true"));

						}
						if (abdominSign.contains("Abdominal Distension")) {
							model.addLiteral(unique_1, hasAbdominalDistension,
									ResourceFactory.createTypedLiteral("true"));

							// System.out.println("yessssabd");

						}
					}

					catch (Exception e) {
						System.out.println(e);
					}
				}

				// **********************************************************************************************************

				String query10 = " select uhid,assessment_time,abdominal_distinction_value from  apollo.sa_feed_intolerance  WHERE uhid="
						+ "'" + uhid + "'" + "AND abdominal_distinction_value is not null order by assessment_time";
				Statement stmt10 = con.createStatement();
				rec10 = stmt10.executeQuery(query10);
				while (rec10.next()) {
					Date assessment_time = rec10.getDate("assessment_time");

					String abdomin_distinction = rec10.getString("abdominal_distinction_value");
					System.out.println(abdomin_distinction + "abdomindistinction");

					// System.out.println(abdominSign+"abdentryyy");
					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					System.out.println(assesstime + "assess time");
					// System.out.println("query10");

					Individual unique_3 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						System.out.println("unique10" + unique_1);

					}

					try {
						if (abdomin_distinction.contains("Visible Loops")
								|| (abdomin_distinction.contentEquals("Visible Loops"))) {
							model.addLiteral(unique_1, hasVisibleBowelLoop, ResourceFactory.createTypedLiteral("true"));

							// System.out.println("yessssvisible");

						}
					}

					catch (Exception e) {
						System.out.println(e);
					}
				}

				// ****************************************************************************************************************

				String query11 = " select  uhid,entry_timestamp,stool  from apollo.nursing_intake_output WHERE uhid="
						+ "'" + uhid + "'" + " AND stool='Blood' order by entry_timestamp";
				Statement stmt11 = con.createStatement();
				rec11 = stmt11.executeQuery(query11);
				while (rec11.next()) {
					Date assessment_time = rec11.getDate("entry_timestamp");

					String blood_stool = rec11.getString("stool");
					// System.out.println(blood_stool+"blood in stool");

					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					// System.out.println(assesstime+"assess time");
					// System.out.println("query11");

					Individual unique_4 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique11"+unique_1);

					}

					model.addLiteral(unique_1, bloodPresentInStool, ResourceFactory.createTypedLiteral("true"));

					// System.out.println("yessssvisible");
				}

				// ***************************************************************************************************************

				String query12 = " Select be,entrydate,uhid from apollo.nursing_bloodgas  WHERE uhid=" + "'" + uhid
						+ "'" + " AND be is not null order by entrydate";
				Statement stmt12 = con.createStatement();
				rec12 = stmt12.executeQuery(query12);

				String baseaccess1;
				float baseaccess = 0;
				while (rec12.next()) {
					Date assessment_time = rec12.getDate("entrydate");

					baseaccess1 = rec12.getString("be");
					// System.out.println("hiiiiii");

					if ((baseaccess1 == null) || (baseaccess1.isBlank())) {
					}

					else {
						baseaccess = Float.parseFloat(baseaccess1);

					}

					// System.out.println("2");

					// System.out.println(baseaccess+"baseaccess");

					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					System.out.println(assesstime + "assess time");
					Individual unique_5 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique12"+unique_1);

					}

					if (baseaccess < -6) {
						model.addLiteral(unique_1, hasMetabolicAcidosis, ResourceFactory.createTypedLiteral("true"));
						System.out.println("yessssmetabolic");

					}

				}

				// ********************************************************************************************************

				String query13 = " Select uhid,modificationtime, apnea,apnea_duration,episodeid from apollo.nursing_episode WHERE uhid="
						+ "'" + uhid + "'" + " AND apnea='t' order by modificationtime";
				Statement stmt13 = con.createStatement();
				rec13 = stmt13.executeQuery(query13);
				while (rec13.next()) {
					Date assessment_time = rec13.getDate("modificationtime");

					String apnea = rec13.getString("apnea");
					System.out.println(apnea + "apnea");

					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					System.out.println(assesstime + "assess time");
					Individual unique_6 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique13"+unique_1);

					}

					{
						model.addLiteral(unique_1, hasApnea, ResourceFactory.createTypedLiteral("true"));
						// System.out.println("yessssapne");

					}

				}

				// ****************************************************************************************************
				String query14 = " Select resp_system_status,uhid,assessment_time from apollo.sa_resp_rds WHERE uhid="
						+ "'" + uhid + "'" + " AND resp_system_status='Yes' order by assessment_time";
				Statement stmt14 = con.createStatement();
				rec14 = stmt14.executeQuery(query14);
				while (rec14.next()) {
					Date assessment_time = rec14.getDate("assessment_time");

					String resp_system = rec14.getString("resp_system_status");
					System.out.println(resp_system + "resp_system");

					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					System.out.println(assesstime + "assess time");
					System.out.println("query14");

					Individual unique_7 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique14"+unique_1);

					}

					{
						model.addLiteral(unique_1, hasRespiratoryDistress, ResourceFactory.createTypedLiteral("true"));
						// System.out.println("yessssapne");

					}

				}

				// *****************************************************************************************************

				String query15 = " Select symptomatic_value,assessment_time,uhid from apollo.sa_infection_sepsis WHERE uhid="
						+ "'" + uhid + "'" + " AND symptomatic_value is not null";
				Statement stmt15 = con.createStatement();
				rec15 = stmt15.executeQuery(query15);
				while (rec15.next()) {
					Date assessment_time = rec15.getDate("assessment_time");

					String symp = rec15.getString("symptomatic_value");

					System.out.println(symp + "symptomatic");

					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					System.out.println(assesstime + "assess time");
					System.out.println("query15");

					Individual unique_8 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique15"+unique_1);

					}

					if (symp.contains("Lethargy")) {
						model.addLiteral(unique_1, hasLethargy, ResourceFactory.createTypedLiteral("true"));
						// System.out.println("yesslethargy");

					}

				}

				// ***********************************************************************************************

				// ****************************************************************************************************
				String query16 = " Select centraltemp,uhid,entrydate from apollo.nursing_vitalparameters WHERE uhid="
						+ "'" + uhid + "'" + " AND centraltemp<36.5 or centraltemp>37.5 order by entrydate";
				Statement stmt16 = con.createStatement();
				rec16 = stmt16.executeQuery(query16);
				Date newtime = null;
				int count = 0;

				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.HOUR_OF_DAY, 10);

				while (rec16.next()) {
					Date assessment_time = rec16.getDate("entrydate");

					/*
					 * long assess = datetime2.getTime()-assessment_time.getTime();
					 * int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);
					 * 
					 * 
					 * if(newtime!=null)
					 * {
					 * long assess1 = assessment_time1.getTime()-newtime.getTime();
					 * 
					 * int centraltime = (int) TimeUnit.MILLISECONDS.toHours(assess1);
					 * 
					 * System.out.println(assessment_time1+"assessment time1..........");
					 * System.out.println(newtime+"new time1.........");
					 * 
					 * System.out.println(centraltime+"centraltmp........");
					 * 
					 * if(centraltime<6 &centraltime>0)
					 * {
					 * count=count+1;
					 * System.out.println(count+"count value");
					 * 
					 * }
					 * 
					 * }
					 * 
					 * 
					 * 
					 * 
					 * 
					 * newtime = assessment_time1;
					 * 
					 * 
					 * //System.out.println(assesstime+"assess time");
					 * Individual unique_8 = null;
					 * 
					 * if(model.getIndividual(med+uhid+"_"+assesstime) != null)
					 * {
					 * 
					 * unique_8= (Individual) model.getIndividual(med+uhid+"_"+assesstime);
					 * }
					 * 
					 * else {
					 * unique_8 = (Individual)
					 * model.createIndividual(med+uhid+"_"+assesstime,model.getOntClass(
					 * "http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
					 * }
					 * 
					 * 
					 * if(count>1)
					 * 
					 * {
					 * model.addLiteral(unique_8,hasTemperatureInStability,ResourceFactory.
					 * createTypedLiteral("true"));
					 * 
					 * 
					 * }
					 */
				}

				// *****************************************************************************************************

				String query17 = " Select prn,itemname,itemvalue,resultdate from apollo.test_result where prn=" + "'"
						+ uhid + "'" + "AND itemname='PLATELET COUNT' AND itemvalue is not null";
				Statement stmt17 = con.createStatement();
				// rec17 = stmt17.executeQuery(conversion("query17",prop1));
				rec17 = stmt17.executeQuery(query17);

				while (rec17.next()) {

					Date assessment_time = rec17.getDate("resultdate");

					int platelet = rec17.getInt("itemvalue");

					System.out.println(platelet + "symptomatic");

					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					// System.out.println(assesstime+"assess time");
					Individual unique_10 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique17"+unique_1);

					}

					if (platelet < 150000) {
						model.addLiteral(unique_1, haslowPlateletCount, ResourceFactory.createTypedLiteral("true"));
						// System.out.println("yesspletelet");

					}

				}

				// ************************************************************************************************************************************

				String query18 = " select entrydate,uhid, baby_color from apollo.nursing_vitalparameters where uhid="
						+ "'" + uhid + "'" + "AND  baby_color='Cyanosis'";
				Statement stmt18 = con.createStatement();
				rec18 = stmt18.executeQuery(query18);

				while (rec18.next()) {

					Date assessment_time = rec18.getDate("entrydate");

					String discolor = rec18.getString("baby_color");

					System.out.println(discolor + "discolor");

					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					// System.out.println(assesstime+"assess time");
					// System.out.println("query18");

					Individual unique_11 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique18"+unique_1);

					}

					model.addLiteral(unique_1, hasSkinDiscoloration, ResourceFactory.createTypedLiteral("true"));

				}

				// ***********************************************************************************************************************

				String query19 = " select  assessment_time,feed_intolerance_status,gastric_aspirate,abdominal_signs,vomit_color,feed_intolerance_status from  apollo.sa_feed_intolerance where uhid="
						+ "'" + uhid + "'" + " order by assessment_time";
				Statement stmt19 = con.createStatement();
				rec19 = stmt19.executeQuery(query19);

				while (rec19.next()) {

					Date assessment_time = rec19.getDate("assessment_time");

					String gastric_aspirate = rec19.getString("gastric_aspirate");
					System.out.println(gastric_aspirate + "gastricccccccccccccccccccccccccccccccccccccccccccc");

					String feed_intol = rec19.getString("feed_intolerance_status");
					System.out.println(feed_intol + "feedintol");

					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					// System.out.println(assesstime+"assess time");
					// System.out.println("query19");

					Individual unique_12 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						unique_1 = (Individual) model.getIndividual(med + uhid + "_" + assesstime);
					}

					else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique19"+unique_1);

					}

					model.addLiteral(unique_1, hasGastricAspirate, ResourceFactory.createTypedLiteral("true"));

					if (feed_intol.contains("Yes")) {
						model.addLiteral(unique_1, hasFeedIntolerance, ResourceFactory.createTypedLiteral("true"));
						// System.out.println(feed_intol+"yesfeedintol");

					}

					if (feed_intol.contentEquals("No")) {
						model.addLiteral(unique_1, hasFeedIntolerance,
								ResourceFactory.createTypedLiteral("false", XSDDatatype.XSDboolean));
						// System.out.println("feedNooooooooooooooooooooooooooooooooooooooooooo");

					}

					if (feed_intol.isBlank()) {
						model.addLiteral(unique_1, hasFeedIntolerance,
								ResourceFactory.createTypedLiteral("false", XSDDatatype.XSDboolean));
						// System.out.println("feednullllllllllllllllllllllllllllllllllllllll");

					}

				}

				// ***********************************************************************************************************************

				// ************************************************************************************************************************************

				String query20 = " select  aspirate_color, entry_timestamp from  apollo.nursing_intake_output where uhid="
						+ "'" + uhid + "'" + " AND aspirate_color is not null order by entry_timestamp";
				Statement stmt20 = con.createStatement();
				rec20 = stmt20.executeQuery(query20);

				while (rec20.next()) {

					Date assessment_time = rec20.getDate("entry_timestamp");

					String aspiratecolor = rec20.getString("aspirate_color");
					// System.out.println(aspiratecolor+"gastric");

					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					// System.out.println(assesstime+"assess time");
					// System.out.println("query20");

					Individual unique_13 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique20"+unique_1);

					}

					if (aspiratecolor.contains("Milky")) {
						model.addLiteral(unique_1, hasGastricAspirateAbnormalColor,
								ResourceFactory.createTypedLiteral("false"));

					} else {
						model.addLiteral(unique_1, hasGastricAspirateAbnormalColor,
								ResourceFactory.createTypedLiteral("true"));
					}

				}
				// ***********************************************************************************************************************

				String query21 = " select entrydate, cft,peripheraltemp,centraltemp,meanibp,symptomatic_status,symptomatic_value from apollo.nursing_vitalparameters 	where uhid="
						+ "'" + uhid + "'";
				Statement stmt21 = con.createStatement();
				rec21 = stmt21.executeQuery(query21);

				while (rec21.next()) {

					Date assessment_time = rec21.getDate("entrydate");
					String bp = rec21.getString("meanibp");

					String cft = rec21.getString("cft");
					float centraltemp = rec21.getFloat("centraltemp");
					float peripheraltemp = rec21.getFloat("peripheraltemp");
					float diff = 0;
					diff = centraltemp - peripheraltemp;

					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					// System.out.println(assesstime+"assess time");
					// System.out.println("query21");

					Individual unique_14 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {

						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique21"+unique_1);

					}

					if (cft != null)
						if (cft.equalsIgnoreCase("3") || (cft.equalsIgnoreCase(">3")))

						{
							model.addLiteral(unique_1, hasLongCapillaryRefillTime,
									ResourceFactory.createTypedLiteral("true"));
						}

					if (diff != 0) {
						model.addLiteral(unique_1, hasCentralPeripheralDifference,
								ResourceFactory.createTypedLiteral("true"));
					}

					if (bp != null) {
						model.addLiteral(unique_1, hasBloodPressure, ResourceFactory.createTypedLiteral(bp));

					}
				}

				// ***********************************************************************************************************************

				String query22 = " select creationtime,symptomatic_value,symptomatic_status from apollo.nursing_episode	where uhid="
						+ "'" + uhid + "'" + "AND symptomatic_status is not null";
				Statement stmt22 = con.createStatement();
				rec22 = stmt22.executeQuery(query22);

				while (rec22.next()) {

					Date assessment_time = rec22.getDate("creationtime");

					String techycardia = rec22.getString("symptomatic_value");

					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					// System.out.println(assesstime+"assess time");
					// System.out.println("query22");

					Individual unique_15 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique22"+unique_1);

					}

					if (techycardia.contains("Tachycardia")) {
						model.addLiteral(unique_1, hasTechycardia, ResourceFactory.createTypedLiteral("true"));
					}
				}

				// ***********************************************************************************************************************
				String query23 = "select assessment_time, peripheries,lactate from apollo.sa_shock where uhid=" + "'"
						+ uhid + "'";
				Statement stmt23 = con.createStatement();
				rec23 = stmt23.executeQuery(query23);
				String peripheries = null;
				String lactate = null;
				Individual unique_16 = null;

				while (rec23.next()) {

					Date assessment_time = rec23.getDate("assessment_time");

					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					System.out.println(assesstime + "assess time");
					System.out.println("23");

					peripheries = rec23.getString("peripheries");

					lactate = rec23.getString("lactate");

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {
						//
						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique23"+unique_1);

					}

					if (peripheries != null)

						if (peripheries.contains("cold")) {
							model.addLiteral(unique_1, hasColdPeripheries, ResourceFactory.createTypedLiteral("true"));
						}

				}

				// ***********************************************************************************************************************

				// ***********************************************************************************************************************
				String query24 = "select eventstatus from apollo.sa_infection_necwhere uhid=" + "'" + uhid + "'"
						+ "AND eventstatus is not null";
				Statement stmt24 = con.createStatement();
				rec24 = stmt24.executeQuery(query23);

				while (rec24.next()) {

					Date assessment_time = rec24.getDate("/");

					String eventstatusNEC = rec24.getString("eventstatus");
					//
					long assess = datetime2.getTime() - assessment_time.getTime();
					int assesstime = (int) TimeUnit.MILLISECONDS.toDays(assess);

					System.out.println(assesstime + "assess time");
					System.out.println("24");

					Individual unique_17 = null;

					if (model.getIndividual(med + uhid + "_" + assesstime) == null) {

						// unique_1 = (Individual) model.getIndividual(med+uhid+"_"+assesstime);
						// }

						// else {
						unique_1 = (Individual) model.createIndividual(med + uhid + "_" + assesstime, model
								.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));
						// System.out.println("unique24"+unique_1);

					}

					if (eventstatusNEC.contains("yes")) {
						model.addLiteral(unique_1, hasNecrotizingEnterocolitis,
								ResourceFactory.createTypedLiteral("true"));
					}

				}
				// ***********************************************************************************************************************

				OntClass uniqueid = null;

				{
					for (long p = doa_DOL; p <= currentDOL; p++)
					// { ExtendedIterator classes = model.listClasses();
					// while (classes.hasNext())
					// {
					// OntClass cls = (OntClass) classes.next();
					// System.out.print(cls.getLocalName());
					// for (Iterator i = cls.listSubClasses(true); i.hasNext();)
					{
						// OntClass c = (OntClass) i.next();

						// if (c.getLocalName().contentEquals("Neonate"))

						{

							// ins = (Individual) model.createIndividual(med+uhid, c);
							// unique_1 = (Individual) model.createIndividual(med+uhid+"_"+p,c);

							unique_1 = (Individual) model.createIndividual(med + uhid + "_" + p, model
									.getOntClass("http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate"));

							/*
							 * String sql =
							 * "select entrydatetime,totalfeed_ml_per_kg_day,babyfeedid, working_weight from apollo.babyfeed_detail where uhid ="
							 * +"'"+uhid+"'";
							 * 
							 * rec2 = stmt.executeQuery(sql);
							 * ResultSetMetaData rsmd = (ResultSetMetaData) rec2.getMetaData();
							 * int numCols = rsmd.getColumnCount();
							 * 
							 * ArrayList <String> atmsend= new ArrayList<String>();
							 * int q=0;
							 * Set<String> hs = new HashSet<String>();
							 * 
							 * try {
							 * ` while(rec2.next())
							 * {
							 * // System.out.println(rec2.getString("entrydatetime")+"  "+rec2.getString(
							 * "totalfeed_ml_per_kg_day")+" "+rec2.getString("babyfeedid")+" "+rec2.
							 * getDouble("working_weight"));
							 * Date entrydatetime1=rec2.getDate("entrydatetime");
							 * extnum=rec2.getString("entrydatetime");
							 * 
							 * 
							 * long diff = entrydatetime1.getTime() - datetime1.getTime();
							 * 
							 * dayoflife = (int) TimeUnit.MILLISECONDS.toDays(diff);
							 * 
							 * System.out.println("Day of life is:");
							 * System.out.println(dayoflife);
							 * model.add(ins, hasDayOfLife,ResourceFactory.createTypedLiteral(dayoflife));
							 * model.addLiteral(ins,
							 * hasGestationalAgeAtBirth,ResourceFactory.createTypedLiteral(gestationalweek,
							 * XSDDatatype.XSDdouble));
							 * model.addLiteral(ins,
							 * hasRiskFactor,ResourceFactory.createTypedLiteral("false",XSDDatatype.
							 * XSDboolean));
							 * model.addLiteral(ins,
							 * hasFeedIntolerance,ResourceFactory.createTypedLiteral("false",XSDDatatype.
							 * XSDboolean));
							 * model.addLiteral(ins, hasBabyID,ResourceFactory.createTypedLiteral(uhid));
							 * 
							 * 
							 * }
							 * }
							 * catch(NullPointerException e) {
							 * System.out.println("Null pointer exception");
							 * }
							 * 
							 */
							// *******************************************\\\

							if (currentDOL >= 0 & gestationalweek != null) {
								model.addLiteral(unique_1, hasDayOfLife, ResourceFactory.createTypedLiteral(p));
								model.addLiteral(unique_1, hasGestationalAgeAtBirth,
										ResourceFactory.createTypedLiteral(gestationalweek, XSDDatatype.XSDdouble));
								// model.addLiteral(unique_1,
								// hasRiskFactor,ResourceFactory.createTypedLiteral("false",XSDDatatype.XSDboolean));
								// model.addLiteral(unique_1,
								// hasFeedIntolerance,ResourceFactory.createTypedLiteral("false",XSDDatatype.XSDboolean));
								model.add(unique_1, hasBabyID, ResourceFactory.createResource(med + uhid));

								if (chestcomp != null)

								{
									model.addLiteral(unique_1, hasChestCompressionDuration,
											ResourceFactory.createTypedLiteral(chestcomp));
								}
								if (ph != null)

								{
									model.addLiteral(unique_1, hasCordGaspHValue,
											ResourceFactory.createTypedLiteral(ph));
								}

								if (be != null)

								{
									model.addLiteral(unique_1, hasBaseDeficitValue,
											ResourceFactory.createTypedLiteral(be));
								}

								if (babytype != null)

								{
									model.addLiteral(unique_1, hasMonoTwinStatus,
											ResourceFactory.createTypedLiteral(twin_status));
								}

								if (val != null)

								{
									model.addLiteral(unique_1, pressorGiven, ResourceFactory.createTypedLiteral(val));
								}

							}

						}

					}

				}

				{

					/*
					 * if(rec5.getString("be")!=null)
					 * {
					 * model.addLiteral(unique,
					 * hasBaseDeficitValue,ResourceFactory.createTypedLiteral(rec5.getString("be")))
					 * ;
					 * 
					 * }
					 */
					// }

					List<Rule> rules = Rule.rulesFromURL("ApolloGuideline.rules");
					GenericRuleReasoner reasoner = (GenericRuleReasoner) GenericRuleReasonerFactory.theInstance()
							.create(null);
					((FBRuleReasoner) reasoner).setRules(rules);
					((GenericRuleReasoner) reasoner).setMode(GenericRuleReasoner.HYBRID);
					reasoner.setDerivationLogging(true);
					InfModel inf = ModelFactory.createInfModel(reasoner, model);

					// org.apache.jena.rdf.model.RDFWriter writer = model.getWriter("Turtle");
					// File file= new File("D:\\CHIL\\Nutrition\\infmodel.ttl");
					// writer.write(inf, new FileOutputStream(file), med);

					String test4 = "PREFIX CHIL: <http://www.childhealthimprints.com/SampleOntology/Testing/>"
							+ "SELECT  DISTINCT ?Baby   WHERE{"
							+ " { ?Baby    <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>  <http://www.childhealthimprints.com/SampleOntology/Testing/#Neonate>}"
							+ "{?Baby   CHIL:hasFeedingVolume  ?FeedVolume} "
							+ "}";

					String test1 = "PREFIX CHIL: <http://www.childhealthimprints.com/SampleOntology/Testing/>"
							+ "PREFIX type:   <http://www.w3.org/2001/XMLSchema#>"
							+ "SELECT  Distinct ?UHID    ?DayOfLife ?FeedVolume  ?FeedAdvancement ?vomitvol ?vomitcolor ?abdtender ?abddistension"
							+ " ?visiblebowel ?bloodstool ?metabolic ?Apnea  ?respDistress ?Lethargy  ?plateletcount  ?skindiscolor "
							+ "?feedint ?gastric  ?CFL ?central  ?techycardia  ?coldperi  ?NEC ?BP WHERE {"
							+ "{?Baby   CHIL:hasBabyID  ?UHID}"
							+ " {?Baby    CHIL:hasDayOfLife  ?DayOfLife}"
							+ "{ ?Baby       CHIL:hasFeedingVolume   ?FeedVolume}"
							+ "{ ?Baby       CHIL:hasFeedingAdvancement   ?FeedAdvancement}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasVomitVolume   ?vomitvol}"
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasVomitColor   ?vomitcolor}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasAbdominalTenderness   ?abdtender}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasAbdominalDistension   ?abddistension}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasVisibleBowelLoop   ?visiblebowel}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:bloodPresentInStool   ?bloodstool}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasMetabolicAcidosis   ?metabolic}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasApnea   ?Apnea}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasRespiratoryDistress  ?respDistress}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasLethargy   ?Lethargy}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:haslowPlateletCount   ?plateletcount}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasSkinDiscoloration   ?skindiscolor}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasFeedIntoleranceSign   ?feedint}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasGastricAspirateAbnormalColor   ?gastric}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasLongCapillaryRefillTime   ?CFL}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasCentralPeripheralDifference  ?central}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasBloodPressure   ?blood}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasTechycardia   ?techycardia}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasColdPeripheries   ?coldperi}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasNecrotizingEnterocolitis   ?NEC}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasLowBloodpressure   ?BP}  "

							+ "}orderby ?DayOfLife";

					String test2 = "PREFIX CHIL: <http://www.childhealthimprints.com/SampleOntology/Testing/>"
							+ "PREFIX long:   <http://www.w3.org/2001/XMLSchema#long>"
							+ "PREFIX boolean:  <http://www.w3.org/2001/XMLSchema#boolean>"
							+ "SELECT  ?UHID   ?DayOfLife ?FeedVolume  ?FeedAdvancement ?vomitvol ?vomitcolor "
							+ "?feedint  ?gastric  ?central WHERE {"
							+ "{?Baby   CHIL:hasBabyID  ?UHID}"
							+ " {?Baby    CHIL:hasDayOfLife  ?DayOfLife}"
							+ "{ ?Baby       CHIL:hasFeedingVolume   ?FeedVolume}"
							+ "{ ?Baby       CHIL:hasFeedingAdvancement   ?FeedAdvancement}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasVomitVolume   ?vomitvol}"
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasVomitColor   ?vomitcolor}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasFeedIntoleranceSign   ?feedint}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasGastricAspirateAbnormalColor   ?gastric}  "
							+ "OPTIONAL"
							+ "{ ?Baby       CHIL:hasCentralPeripheralDifference  ?central}  "

							+ "}orderby ?DayOfLife";

					Query query = QueryFactory.create(test1);
					QueryExecution qexec = QueryExecutionFactory.create(query, inf);
					ResultSet rs = qexec.execSelect();
					// ResultSetFormatter.out(System.out, rs, query);

					/*
					 * String vomitvol="";
					 * String feedint="";
					 * String central_s="";
					 * String vomitcolor_s="";
					 * String abdominaldist_s="";
					 * String visiblebowel_s="";
					 * String abdtender_s1="";
					 * String abdtender_s11="";
					 * String abdominaldist_s1="";
					 * String visiblebowel_s1="";
					 * String bloodstool_s="";
					 * String metabolic_s="";
					 * String respdistress_s="";
					 * String apnea_s="";
					 * String lethargy_s="";
					 * String plateletcount_s="";
					 * String skindiscolor_s="";
					 * String CFT_s="";
					 * String gastricaspirate_s="";
					 * String techycardia_s = "";
					 * String coldperi_s="";
					 * String NEC_s="";
					 * String bp_s="";
					 * 
					 */
					List vars = rs.getResultVars();

					System.out.println(vars + "varssssssssssss");

					while (rs.hasNext()) { // iterate over the result

						int counter = 0;
						QuerySolution qs = rs.nextSolution();
						System.out.println("solu");
						String uhidValue = "";
						Integer dolValue = 0;
						String feedVolumeValue = "";
						String feedIncrementValue = "";
						String vomitvolume = "";
						String vomitcolor = "";
						String abdominaltenderness = "";
						String abdominaldistension = "";
						String visiblebowel = "";
						String bloodstool = "";
						String metabolic = "";
						String apnea = "";
						String respiratorydistress = "";
						String lethargy = "";
						String plateletcount = "";
						String skindiscolor = "";
						String feedintolerance = "";
						String gastricaspirate = "";
						String CFT = "";
						String central = "";
						String techycardia = "";
						String coldperi = "";
						String NEC = "";
						String details = "";
						String BPvalue = "";
						String risk = "";
						String value = "";
						String vomitvol = "";
						String feedint = "";
						String central_s = "";
						String vomitcolor_s = "";
						String abdominaldist_s = "";
						String visiblebowel_s = "";
						String abdtender_s1 = "";
						String abdtender_s11 = "";
						String abdominaltender_s = "";
						String visiblebowel_s1 = "";
						String bloodstool_s = "";
						String metabolic_s = "";
						String respdistress_s = "";
						String apnea_s = "";
						String lethargy_s = "";
						String plateletcount_s = "";
						String skindiscolor_s = "";
						String CFT_s = "";
						String gastricaspirate_s = "";
						String techycardia_s = "";
						String coldperi_s = "";
						String NEC_s = "";
						String bp_s = "";

						for (int i = 0; i < vars.size(); i++) {
							details = "nil";

							String var = vars.get(i).toString();
							if (var.contentEquals("UHID")) {
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									// System.out.println("UHID:........"+ "" + value);
									uhidValue = value;
								}
							}

							else if (var.contentEquals("DayOfLife")) {
								RDFNode node = qs.get(var);
								value = node.toString().split("http")[0];
								value = value.replaceAll("[^a-zA-Z0-9]", "");

								dolValue = Integer.parseInt(value);
								// System.out.println("dolValue:........"+ "" + dolValue);
							} else if (var.contentEquals("FeedVolume")) {
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									// System.out.println("FeedVolume:........"+ "" + value);
									feedVolumeValue = value;
								}
							} else if (var.contentEquals("FeedAdvancement")) {
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									// System.out.println("FeedAdvancement........"+ "" + value);
									feedIncrementValue = value;
								}
							} else if (var.contentEquals("vomitvol")) {
								vomitvol = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									// System.out.println("vomitvolume."+value);
									if (!value.contentEquals("nil")) {
										vomitvolume = value;
										vomitvol = ("hasVomitVolume:" + vomitvolume);
									} else
										vomitvol = "";
									// System.out.println("vomitvolume........"+ "" + vomitvolume);
								}
							}

							else if (var.contentEquals("feedint")) {
								feedint = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									// System.out.println(value+ "feedintolerance22222222");
									if (!value.contentEquals("nil")) {
										feedintolerance = value;
										// details.concat("hasFeedIntolerance:"+feedintolerance);
										feedint = "  hasFeedIntolerance:" + feedintolerance;
									} else
										feedint = "";
									// System.out.println("feedintolerance........"+ feedintolerance);
								}
							} else if (var.contentEquals("central")) {
								central_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										central = value;
										central_s = "  hasCentralPeripheralDifference:" + central;
									} else
										central_s = "";
									// System.out.println("centralperipheraldifference........"+ central);
								}
							}

							else if (var.contentEquals("vomitcolor")) {
								vomitcolor_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										vomitcolor = value;
										vomitcolor_s = "  hasvomitcolor:" + vomitcolor;
									} else
										vomitcolor_s = "";
									// System.out.println("vomitcolor........"+ vomitcolor);
								}
							} else if (var.contentEquals("abdtender")) {
								abdominaltender_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										abdominaltenderness = value;
										abdominaltender_s = "  abdominaltenderness:" + abdominaltenderness;
									} else
										abdominaltender_s = "";
									// System.out.println("abdominaltender........"+ abdominaltenderness);
								}
							} else if (var.contentEquals("abddistension")) {
								abdominaldist_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										abdominaldistension = value;
										abdominaldist_s = "  hasAbdominalDistension:" + abdominaldistension;
									} else
										abdominaldistension = "";
									// System.out.println("abdominaldistension........"+ abdominaldistension);
								}
							} else if (var.contentEquals("visiblebowel")) {
								visiblebowel_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										visiblebowel = value;
										visiblebowel_s = "  hasVisibleBowel:" + visiblebowel;
									} else
										visiblebowel = "";
									// System.out.println("visiblebowel........"+ visiblebowel);
								}
							} else if (var.contentEquals("bloodstool")) {
								bloodstool_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										bloodstool = value;
										bloodstool_s = "  hasbloodinstool:" + bloodstool;
									} else
										bloodstool_s = "";
									// System.out.println("bloodinstool........"+ bloodstool);
								}
							} else if (var.contentEquals("metabolic")) {
								metabolic_s = "";
								RDFNode node = qs.get(var);
								// System.out.println("Node........"+ node);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										metabolic = value;
										metabolic_s = "  hasmetabolicacidosis:" + metabolic;
									} else
										metabolic_s = "";
									// System.out.println("metabolicacidosis......."+ details1);
								}
							} else if (var.contentEquals("Apnea")) {
								apnea_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										apnea = value;
										apnea_s = "  hasapnea:" + apnea;
									} else
										apnea_s = "";
									// System.out.println("apnea........"+ apnea);
								}
							} else if (var.contentEquals("respDistress")) {
								respdistress_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										respiratorydistress = value;
										respdistress_s = "  hasrespiratoryDistress:" + respiratorydistress;
									} else
										respdistress_s = "";
								}
							} else if (var.contentEquals("Lethargy")) {
								lethargy_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										lethargy = value;
										lethargy_s = "  hasLethargy:" + lethargy;
									} else
										lethargy_s = "";
									// System.out.println("haslethargy........"+ lethargy);
								}
							} else if (var.contentEquals("plateletcount")) {
								plateletcount_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										plateletcount = value;
										plateletcount_s = "  hasplateletcount:" + plateletcount;
									} else
										plateletcount_s = "";
									// System.out.println("hasplateletcount........"+ plateletcount);
								}
							} else if (var.contentEquals("skindiscolor")) {
								skindiscolor_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										skindiscolor = value;
										skindiscolor_s = "  hasskindiscolor:" + skindiscolor;
									} else
										skindiscolor_s = "";
									// System.out.println("hasskindiscolor........"+ skindiscolor);
								}
							} else if (var.contentEquals("gastric")) {
								gastricaspirate_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										gastricaspirate = value;
										gastricaspirate_s = "  hasgastricaspirate:" + gastricaspirate;
										// System.out.println("gastricaspirate........"+ gastricaspirate);
									} else
										gastricaspirate_s = "";
								}
							} else if (var.contentEquals("CFL")) {
								CFT_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										CFT = value;
										CFT_s = "  hasLongCapillaryRefillTime:" + CFT;
										// System.out.println("CFL........"+ CFT);
									} else
										CFT_s = "";
								}
							}

							else if (var.contentEquals("techycardia")) {
								techycardia_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										techycardia = value;
										techycardia_s = "  hastechycardia:" + techycardia;
									} else
										techycardia_s = "";
									// System.out.println("hastechycardia........"+ techycardia);
								}
							} else if (var.contentEquals("coldperi")) {
								coldperi_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										coldperi = value;
										coldperi_s = "  hasColdPeripheries" + coldperi;
										// System.out.println("coldperi........"+ coldperi);
									} else
										coldperi_s = "";
								}
							} else if (var.contentEquals("NEC")) {
								NEC_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										NEC = value;
										NEC_s = "  hasNecrotizingEnterocolitis" + NEC;
									} else
										NEC_s = "";
									// System.out.println("NEC........"+ NEC);
								}
							} else if (var.contentEquals("BP")) {
								bp_s = "";
								RDFNode node = qs.get(var);
								if (node != null) {
									value = node.toString();
									if (!value.contentEquals("nil")) {
										BPvalue = value;
										bp_s = "  hasBP" + BPvalue;
									} else
										bp_s = "";
									// System.out.println("hasBP........"+ BP);
								}
							}

							details = vomitcolor_s + visiblebowel_s + vomitvol + feedint + central_s + abdominaldist_s
									+ abdominaltender_s + bloodstool_s + metabolic_s + respdistress_s + apnea_s
									+ lethargy_s + plateletcount_s + skindiscolor_s + CFT_s + gastricaspirate_s
									+ techycardia_s + coldperi_s + NEC_s + bp_s;
						}
						if (details.contentEquals(""))
							details = " ";
						System.out.println("hasdetailssssssss........" + details);

					}

					// + "?feedint ?gastric ?CFL ?central ?techycardia ?coldperi ?NEC

					// Result to be saved: UHID,DOL, FeedVolume, FeedAdvancement

					/*
					 * Query query = QueryFactory.create(test2) ;
					 * QueryExecution qexec = QueryExecutionFactory.create(query, inf) ;
					 * ResultSet rs = qexec.execSelect() ;
					 * List vars=rs.getResultVars();
					 * 
					 * // ResultSetFormatter.out(System.out, rs, query);
					 * while (rs.hasNext()) { // iterate over the result
					 * QuerySolution qs=rs.nextSolution();
					 * System.out.println("solu");
					 * for(int i=0;i<vars.size();i++)
					 * { String var=vars.get(i).toString();
					 * RDFNode node=qs.get(var);
					 * System.out.println(var+"\t"+node.toString());
					 * 
					 * }
					 * }
					 */

				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
