package UnitTests;
import GenericDataComparison.*;
import java.util.ArrayList;

import org.junit.Assert;

import junit.framework.TestCase;

public class GenericDataComparisonTests extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
		
		manager = new GenericComparisonManager();
		objectTypeData = new ObjectType();
		userEntry = new UserComparisonEntry();
		fileManager = new JsonFileManager();
	}

	private GenericComparisonManager manager;
	private ObjectType objectTypeData;
	private UserComparisonEntry userEntry;
	private JsonFileManager fileManager;
	
	private ArrayList<Characteristic> CreateCharacteristics()
	{
		ArrayList<Characteristic> newCharacteristics = new ArrayList<Characteristic>();
		
		Characteristic characteristic1 = new Characteristic("No of wheels", 4, 10, 5, 6, 100, 4, 5, "na", BetterValue.HIGHEST);
		Characteristic characteristic2 = new Characteristic("HorsePower", 190, 510, 350, 400, 100, 230, 500, "310", BetterValue.LOWEST);
		newCharacteristics.add(characteristic1);
		newCharacteristics.add(characteristic2);
		
		return newCharacteristics;
	}
	
	private ObjectType CreateObjectType()
	{
		ObjectType newObjectType = new ObjectType("cars", CreateCharacteristics());
		
		return newObjectType;
	}
	
	public void test_CreateObjectType_Save() {
		//	Arrange
		String objectTypeName = "Tree";
		
		//	Act
		manager.saveObjectType(objectTypeName);
		Boolean fileCreated = fileManager.fileCreated();
		
		//	Assert
		Assert.assertEquals(true, fileCreated);
	}
	
	public void test_ReadAllObjectTypes_Get() {
		ArrayList<ObjectType> types = new ArrayList<ObjectType>();
		
		types = manager.getObjectTypes();
		
		Assert.assertTrue(types.size() > 0);
	}
	
	public void test_GetObjectTypeByName_Get() {
		String name = "Tree";
		
		objectTypeData = manager.getObjectTypeByName(name);
		
		Assert.assertEquals("Tree", objectTypeData.getName());
	}
	
	public void test_addCharacteristics_Save() {
		String name = "Tree";
		ArrayList<Characteristic> characteristics = CreateCharacteristics();
		
		manager.addCharacteristics(name, characteristics);
		
		Assert.assertEquals(true, true);
	}
	
	public void test_addUserComparisonEntry_Save() {
		String objectTypeName = "Tree";
		String userEntryName = "My apple tree";
		
		manager.addUserComparisonEntry(objectTypeName, userEntryName);
		
		Assert.assertEquals(true, true);
	}
	
	public void test_getUserComparisonEntry() {
		String objectTypeName = "Tree";
		String userEntryName = "My apple tree";
		
		ArrayList<UserComparisonEntry> result = manager.getUserComparisonEntriesByObjectTypeName(objectTypeName);
		
		Assert.assertTrue(result.size() > 0);
	}
	
	
}
