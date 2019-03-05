package org.onap.ccsdk.sli.northbound.uebclient;
 
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.onap.sdc.tosca.parser.api.ISdcCsarHelper;
import org.onap.sdc.toscaparser.api.elements.Metadata;
 
 public class SdncServiceModelTest {
 
	SdncServiceModel testSdncServiceModel = null;
	 
	@Before
 	public void setUp() throws Exception {
		ISdcCsarHelper mockCsarHelper = mock(ISdcCsarHelper.class);
		Metadata mockMetadata = mock(Metadata.class);
		
		when(mockCsarHelper.getMetadataPropertyValue(mockMetadata, "UUID")).thenReturn("aaaa-bbbb-cccc-dddd");
		when(mockCsarHelper.getMetadataPropertyValue(mockMetadata, "invariantUUID")).thenReturn("bbbb-cccc-dddd-eeee");
		when(mockCsarHelper.getMetadataPropertyValue(mockMetadata, "namingPolicy")).thenReturn("test-naming-policy");
		
		testSdncServiceModel = new SdncServiceModel(mockCsarHelper,mockMetadata);

 		assertNotNull(testSdncServiceModel);
 	}
 	
	@Test 
	public void testSetGetServiceUUID() { 
		String newServiceUuid = "cccc-dddd-eeee-ffff";
		testSdncServiceModel.setServiceUUID(newServiceUuid);
		String result = testSdncServiceModel.getServiceUUID(); 
		assertEquals("\"" + newServiceUuid + "\"", result); 
	} 
 
	@Test 
	public void testSetGetServiceInvariantUUID() { 
		String result = testSdncServiceModel.getServiceInvariantUUID(); 
		assertEquals(result, "\"bbbb-cccc-dddd-eeee\""); 
	} 
 
	@Test 
	public void testSetGeServiceInstanceNamePrefix() { 
		String serviceInstanceNamePrefix = "test-service-instance-name-prefix";
		testSdncServiceModel.setServiceInstanceNamePrefix(serviceInstanceNamePrefix);
		String result = testSdncServiceModel.getServiceInstanceNamePrefix(); 
		assertEquals(serviceInstanceNamePrefix, result); 
	} 
 
	@Test 
	public void testSetGetResourceVendor() { 
		String resourceVendor = "Fortinet";
		testSdncServiceModel.setResourceVendor(resourceVendor);
		String result = testSdncServiceModel.getResourceVendor(); 
		assertEquals(resourceVendor, result); 
	} 
 
	@Test 
	public void testSetGetResourceVendorRelease() { 
		String resourceVendorRelease = "1.0.0";
		testSdncServiceModel.setResourceVendorRelease(resourceVendorRelease);
		String result = testSdncServiceModel.getResourceVendorRelease(); 
		assertEquals(resourceVendorRelease, result); 
	} 

	@Test 
	public void testGetSqlString() { 
		String result = testSdncServiceModel.getSql("TEST-HELLO"); 
		String test = "INSERT into SERVICE_MODEL (service_uuid, model_yaml, filename, naming_policy, invariant_uuid) values (\"aaaa-bbbb-cccc-dddd\", \"TEST-HELLO\", \"null\", \"test-naming-policy\", \"bbbb-cccc-dddd-eeee\");"; 
		assertEquals(test, result); 
	} 
 

 }