package com.ots.db.operations;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.statement.IBatchStatement;
import org.dbunit.database.statement.StatementFactory;
import org.dbunit.dataset.CachedDataSet;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredTableMetaData;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.FlatXmlWriter;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.zaxxer.hikari.HikariDataSource;


public class DbUnitOperations {
	
	
	public static  IDatabaseConnection dbConn;
	
	public DataSource dataSource; 
	
	public DbUnitOperations(DataSource dataSource ) throws DatabaseUnitException {
		this.dataSource= dataSource;
		createDbConnection();
	}
	
	
	private  void createDbConnection() throws DatabaseUnitException {
		
		Connection jdbcConnection = DataSourceUtils.getConnection(dataSource);
		dbConn= new DatabaseConnection(jdbcConnection);
		DatabaseConfig config =  dbConn.getConfig() ; 
	    config.setProperty(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, Boolean.FALSE);
		config.setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
	}
	
	//***************************************************************************************************************************************************************************************************
	public IDataSet createDataSetForTables(String[] tablenames , IDatabaseConnection iDatabaseConnection ) throws DataSetException, SQLException  {
		IDataSet tmpDataset = iDatabaseConnection.createDataSet(tablenames);
		IDataSet actualDataset = new CachedDataSet(tmpDataset);
 		return actualDataset;
	
	}
	
	
	/*public  IDataSet getIDataSetFromDbTables( List<String> dbTables ) throws DataSetException, SQLException{
		
		List<IDataSet>  dbRowsIDataSetsList = new ArrayList<IDataSet>(); 
		for ( String dbTable  : dbTables )  { 
			
			 dbRowsIDataSetsList.add ( this.createDataSetForOneTable(dbTable)  ) ; 
			
		}
		return new CompositeDataSet(dbRowsIDataSetsList.toArray(new IDataSet[dbRowsIDataSetsList.size()])); 	
	}	
	*/
	
	
	
	public IDataSet createDataSetForTables(String[] tablenames  ) throws DataSetException, SQLException  {
		return createDataSetForTables(tablenames , dbConn) ; 
	}
	
	
	
	
	public IDataSet createDataSetForOneTable(   String tableName  ) throws DataSetException, SQLException  {
		return createDataSetForTables( new String[]{  tableName } ) ; 
	}
	
	public IDataSet createDataSetForOneTable(   String tableName , String[] excludeColumns  ) throws DataSetException, SQLException  {
		
		IDataSet  actualDataSet = createDataSetForOneTable( tableName  ); 
		ITable actualTable = actualDataSet.getTable(tableName); 
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable(actualTable,excludeColumns );
		return new CompositeDataSet(new ITable[]{  filteredTable }) ; 

		
		/*DefaultColumnFilter columnFilter = new DefaultColumnFilter(); 
		for (String excludeColumn : excludeColumns) { 
			columnFilter.excludeColumn(excludeColumn); 
		}
		FilteredTableMetaData metaData = new FilteredTableMetaData(actualTable.getTableMetaData(), columnFilter);
		ITable filteredTable = new CompositeTable(metaData, actualTable)
		return new CompositeDataSet(new ITable[]{  filteredTable }) ; 
		*
		*/
		
	}
	
	// Επιστρέφει ένα υποσύνολο δεδομένων ενός πίνακα με βάση το sqlWhereExpression που περνιέται ώς όρισμα.
	public IDataSet getSelectedDatasetFromOneTable( String tableName ,  String sqlWhere ) throws AmbiguousTableNameException  {
		QueryDataSet partDS = new QueryDataSet(dbConn);
		String sqlExpession ="";
		if  (sqlWhere != null ) { 
			 sqlExpession ="SELECT * FROM " + tableName + " where "+ sqlWhere;
			 System.out.println  ( sqlExpession ) ; 
			  partDS.addTable(tableName,sqlExpession);
		} else
			partDS.addTable(tableName) ; 
		 
		
	//Deletes all rows of tables present in the specified dataset. 
	//If the dataset does not contains a particular table, but that table exists in the database, the database table is not affected. 
	//Table are truncated in reverse sequence.	
	
		//	DatabaseOperation.DELETE_ALL.execute(dbConn,	dataSet  	); // 
	
	//	This operation inserts the dataset contents into the database.
	//	This operation assumes that table data does not exist in the target database and fails if this is not the case. 
	//	To prevent problems with foreign keys, tables must be sequenced appropriately in the dataset.
		
		//	DatabaseOperation.INSERT.execute(dbConn,	dataSet  	);
		 
		return partDS;
		
	}
	
	// Αρχικοποιεί τους πίνακες της βάσης με βάση  με το  dataSetXmlFile
	// Διαγράφει τα data από τους πίνακες  που αναφέρονται στο dataSetXmlFile
	// και εν συνεχεία κάνει import στους αντίστοιχους πίνακες
	public void inititializeDB(String dataSetXmlFile) throws Exception{
		
		
		IDataSet dataSet  = 	getDataSet(dataSetXmlFile); 
	    System.out.println ("****************************** dataSet to import  ******************************") ; 
		FlatXmlDataSet.write(dataSet, new PrintWriter(System.out) , StandardCharsets.UTF_8.toString());
	    System.out.println ("***********************************************************************************") ;
		DatabaseOperation.DELETE_ALL.execute(dbConn,	dataSet  	);
		DatabaseOperation.INSERT.execute(dbConn,	dataSet  	);
	}
	
	// Επιστρέφει ένα υποσύνολο δεδομένων ενός πίνακα με βάση το sqlWhereExpression που περνιέται ώς όρισμα.
	// Στο IDataset εξαιρούνται οι στήλες που προσδιορίζονται στο excludeColumns
	public IDataSet getSelectedDatasetFromOneTable( String tableName ,  String sqlWhere , String[] excludeColumns) throws DataSetException  {
		
		IDataSet  actualDataSet = getSelectedDatasetFromOneTable( tableName , sqlWhere  ); 
		ITable actualTable = actualDataSet.getTable(tableName); 
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable(actualTable,excludeColumns );
		return new CompositeDataSet(new ITable[]{  filteredTable }) ; 
		
	}
	//***************************************************************************************************************************************************************************************************
	
	//Παίρνουμε το IDataSet από την βάση από ένα Map της μορφής <Πίνακας , Sql Expression > 
	// δηλαδή από ποιον πίνακα και με βάση ποια κριτήρια.
	public IDataSet getDatasetFromMapTables(Map<String, String>  tables ) throws DataSetException   {
		
		List<IDataSet>  dbIDataSetsList = new ArrayList<IDataSet>(); 
		for (	Entry<String,String> entry :	tables.entrySet()  ) { 
			String table = entry.getKey(); 
			String sqlWhereExpression = 	entry.getValue(); 
			dbIDataSetsList.add( getSelectedDatasetFromOneTable ( table , sqlWhereExpression ) ) ; 
			
		}
		return new CompositeDataSet(dbIDataSetsList.toArray(new IDataSet[dbIDataSetsList.size()])); 
	}

//***************************************************************************************************************************************************************************************************
	
	//Πάιρνει το σύνολο δεδομένων όλων των πινάκων μιας βάσης.
	public IDataSet getActualDataSetForDataBase(IDatabaseConnection iDatabaseConnection) throws SQLException, DataSetException   {
 		IDataSet tmpDataset = iDatabaseConnection.createDataSet();
	    IDataSet actualDataset = new CachedDataSet(tmpDataset);
	 	return actualDataset;	
 		
 	}
 
 	
	public IDataSet getActualDataSetForDataBase() throws SQLException, DataSetException   {
		return getActualDataSetForDataBase( dbConn) ; 
		
 		
 	}
	//***************************************************************************************************************************************************************************************************
	
	//Σύγκριση δεδομένων ενός IDataSet με τα αντίστοιχα δεδομένα της βάσης - όλα τα rows -  για όλους τους πίνακες που αναφέρονται στο IDataSet   
	// Στο actualDatasSet συμπεριλαμβάνονται όλες οι στήλες των αντίστοιχων πινάκων της βάσης
	// Μπορεί να χρησιμοποιηθεί όταν θέλουμε να ελέγξουμε ότι  δεν έχει μεταβληθεί  κάτι στην βάση . 
	public void compareDataSetWithDataBaseWithOutExcludingColumns(IDataSet dataSet) throws Exception {
	 	
		String[] tablenames = dataSet.getTableNames();
	 	IDataSet actualDataset  = this.createDataSetForTables(tablenames);
	 	for (String tableName : tablenames) {
            ITable table = dataSet.getTable(tableName);
            ITable actualTable = actualDataset.getTable(tableName);         
            Assertion.assertEquals(table,actualTable);
     
	 	}	  
    }
	
	//Σύγκριση δεδομένων ενός IDataSet με τα εντίστοιχα δεδομένα της βάσης  - ολα τα rows - για όλους τους πίνακες που αναφέρονται στο IDataSet  
	//συμπεριλαμβάνονται  και ελέγχονται μόνο  οι στήλες των πινάκων που αναφέρονται στο αντίστοιχο  expectedtable.
	public void compareDataSetWithDataBase(IDataSet dataSet) throws Exception {
		 	
			
	 	//***********************************************************************************************************************
		String[] tablenames = dataSet.getTableNames();
	 	IDataSet actualDataset  = this.createDataSetForTables(tablenames);
	 	compareExpectedDataSetWithActualDataSet(dataSet ,actualDataset ) ; 
	 	
	 	//***********************************************************************************************************************
	}
	
	
	
	//Σύγκριση δεδομένων  μεταξύ δύο IDataSet - το ένα είναι το expectedDataSet  ( το αναμενόμενο που αντιστοιχεί σε αυτό που περιμένουμε ) και το άλλο 
	//είναι το actualDataSet( το πραγματικό , αυτό που λαμβάνουμε από την βάση  ) 
	//Στο actualDatasSet συμπεριλαμβάνονται μόνο  οι στήλες των πινάκων που αναφέρονται στο αντίστοιχο  expectedDataSet.
	public void compareExpectedDataSetWithActualDataSet(IDataSet  expectedDataSet , IDataSet actualDataSet ) throws Exception {
	 	
		String[] tablenames = expectedDataSet.getTableNames();
	 	for (String tableName : tablenames) {
	 		
	 		ITable expectedtable = expectedDataSet.getTable(tableName);
            ITable actualTable = actualDataSet.getTable(tableName);  
            
            ITable filteredActualTable = DefaultColumnFilter.includedColumnsTable(actualTable, 
         			 expectedtable.getTableMetaData().getColumns());
            
            
            /*Column[] expectedColumns = expectedtable.getTableMetaData().getColumns();
    		ITable sortedExpected = new SortedTable(expectedtable, expectedColumns);
    		ITable sortedActual = new SortedTable(filteredActualTable, expectedColumns);
    		Assertion.assertEquals(sortedExpected, sortedActual);*/
    		
    		/*SortedTable sortedTable1 = new SortedTable(expectedtable, new String[]{"CREATED_AT"});
            sortedTable1.setUseComparable(true); // must be invoked immediately after the constructor
            
            SortedTable sortedTable2 = new SortedTable(filteredActualTable, new String[]{"created_at"});
            sortedTable2.setUseComparable(true); // must be invoked immediately after the constructor
*/            
            //new FlatXmlWriter(new PrintWriter(System.out)).write(sortedTable1);
            
            /*System.out.println("----------------- expectedDataSet ---------------------------" ); 
    		new FlatXmlWriter(new PrintWriter(System.out)).write(sortedTable1);
    		*/
    		
          //  Assertion.assertEquals(sortedTable1, sortedTable2);
            
    		
    		Assertion.assertEquals(expectedtable , filteredActualTable);
            
        }
	  
    }

	
	
	
	//*************************************************************************************************************************************************************************
	//This method returns a data set loaded from an XML file on the classpath.

	protected IDataSet getDataSet( String inputXmlFile ) throws Exception
	{
		FlatXmlDataSetBuilder fxmlBuilder=new FlatXmlDataSetBuilder();
		fxmlBuilder.setColumnSensing(true);
		InputStreamReader 	inputStreamReader = new InputStreamReader(new FileInputStream( inputXmlFile),StandardCharsets.UTF_8);
		return fxmlBuilder.build(  new BufferedReader( inputStreamReader ) ) ; 
		
	
	}

	//*************************************************************************************************************************************************************************

	//export a data setinto a flat XML file
	public void  exportData( IDataSet dataSet  , String outputXmlFile ) throws Exception
	{
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(  new FileOutputStream(outputXmlFile) ,   StandardCharsets.UTF_8); 
		 BufferedWriter bufferedWriter =  new BufferedWriter(outputStreamWriter); 
		 FlatXmlDataSet.write(dataSet, bufferedWriter );
	}

	//**************************************************************************************************************************************************************

	
	public static IDatabaseConnection getDbConn() {
			return dbConn;
		}

	
	
	
	
	public void executeOneStatement( String sql ) throws Exception {
		if ( sql == null ) return ; 
		
		String[] sqls = new String[1];
		sqls[0] = sql; 
		executeBatchStatement(sqls) ;
		
	}


	public void executeBatchStatement( String[] sqls) throws Exception {
		
		StatementFactory statementFactory = new StatementFactory();
		
			if (sqls == null) {
				return;
			}
			
			IBatchStatement batch = statementFactory.createBatchStatement(dbConn);
			for (int index = 0; index < sqls.length; index++) {
					batch.addBatch(sqls[index]);
			}
	
	      batch.executeBatch();
	      batch.close();
	}
	
	//********************************************Getters And Setters************************************************************
	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public static void setDbConn(IDatabaseConnection dbConn) {
		DbUnitOperations.dbConn = dbConn;
	}
	
	
}
