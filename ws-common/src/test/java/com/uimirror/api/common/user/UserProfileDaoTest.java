package com.uimirror.api.common.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.uimirror.api.common.base.BaseTest;
import com.uimirror.api.common.base.JUnit4ClassRunner;
import com.uimirror.api.common.register.dao.TempUserDao;
import com.uimirror.api.common.user.dao.UserDao;

/**
 * 
 * ***********************************************************************
 * This class is developed by UIMirror Team.
 * Details of class goes like : will test all the various profile 
 * Related operations to be given to the user.
 * 
 * @author Jayaram
 * @version $
 * @see 
 * @createdOn 22-Mar-2014 8:20:53 PM
 * @modifiedby Jayaram
 * @modifiedon 22-Mar-2014 8:20:53 PM
 * ***********************************************************************
 */
@RunWith(JUnit4ClassRunner.class)
public class UserProfileDaoTest extends BaseTest{
	
	@Autowired
	private TempUserDao registerationDao;
	@Autowired
	private UserDao userProfileDao;

	@Test
	public void testCreateUser() {
		for(Map<String, Object> map :  userProfileTestData()){
			userProfileDao.createUserProfile(map);
		}
	}
	
	@Test
	public void testEditProfile(){
		//userProfileDao.editAboutMe(null);
	}
	
	private List<Map<String, Object>> userProfileTestData() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(5);
    	
    	Map<String, Object> usrMap = new HashMap<String, Object>(1);
    	
    	Map<String, Object> prfMap = new HashMap<String, Object>(30);
    	Map<String, Object> socialprfMap = new HashMap<String, Object>(30);
    	Map<String, Object> extraMap = new HashMap<String, Object>(30);
    	Map<String, Object> prviacyMap = new HashMap<String, Object>(30);
    	
    	//Building name
    	usrMap.put("name", buildNameMap("Jayaram", "Pradhan"));
    	//Building Sex
    	//User has sex, which is public
    	usrMap.put("sex", "M");
    	
    	//User has DOB, which has two type of privacy
    	//1- date privacy and year privacy
    	usrMap.put("dob", buildDateOfBirth("18", "03", "1988", buildPrivacy("onlyme", null, null), buildPrivacy("custom", new String[]{"123", "456"}, null)));
    	
    	//User has religion, which has privacy it may public or may not
    	usrMap.put("religion", buildReligionMap("Hindu", "1", "public", null, null));
    	
    	//Building political View
    	//User has political view, which has privacy it may be public or may not
    	usrMap.put("politicalview", buildPoliticalMap("N Modi For PM", "1", "public", null, null));
    	
    	//Building blood group
    	//user has blood group, which has privacy it may be public, private , friends or custom
    	usrMap.put("bloodgroup", buildbloodMap("B -VE", "2", "public", null, null));
    	
    	//Building family
    	//user has family, family consist of father, mother etc.
    	String[] familyName = new String[]{"Test", "Test2", "Test3"};
    	String[] familyId = new String[]{"123", "456", "789"};
    	String[] familyRelation = new String[]{"Father", "Mother", "Sister"};
    	List<String[]> familyShareWith = new ArrayList<String[]>();
    	familyShareWith.add(null);
    	familyShareWith.add(null);
    	familyShareWith.add(null);
    	List<String[]> familyNotShareWith = new ArrayList<String[]>();
    	familyNotShareWith.add(null);
    	familyNotShareWith.add(null);
    	familyNotShareWith.add(null);
    	usrMap.put("family", buildFamilyMap(familyName, familyId, familyRelation, new String[]{"public","public","public"}, familyShareWith, familyNotShareWith));
    	//User Known Languages
    	//It has privacy
    	usrMap.put("knownlanguage", buildknownLanguageMap(new String[] {"English", "Hindi"}, new String[] {"1", "2"}, "public", null, null));
    	
    	//User places
    	List<String[]> placesShareWith = new ArrayList<String[]>();
    	placesShareWith.add(null);
    	placesShareWith.add(null);
    	placesShareWith.add(null);
    	List<String[]> placesNotShareWith = new ArrayList<String[]>();
    	placesNotShareWith.add(null);
    	placesNotShareWith.add(null);
    	placesNotShareWith.add(null);
    	usrMap.put("place", buildPlacesMap(new String[]{"Bhubaneswar, Orisa", "Bangalore, India", "Pune, India"}, new String[]{"1","2","3"}, new String[]{"hometown","current","visited"}, new String[]{"public","public","public"}, placesShareWith, placesNotShareWith));
    	//User Jobs
    	List<String[]> jobShareWith = new ArrayList<String[]>();
    	jobShareWith.add(null);
    	jobShareWith.add(null);
    	jobShareWith.add(null);
    	List<String[]> jobNotShareWith = new ArrayList<String[]>();
    	jobNotShareWith.add(null);
    	jobNotShareWith.add(null);
    	jobNotShareWith.add(null);
    	String[] companyNames = new String[]{"EMC", "Infosys", "Satyam"};
    	String[] companyId = new String[]{"1", "2", "3"};
    	String[] desgination = new String[]{"Software Developer", "Associate", "Senior Software Developer"};
    	String[] startDate = new String[]{"27-March-2013", "29-Oct-2009", "14-Aprl-2010"};
    	String[] endDate = new String[]{null, "29-Dec-2010", "14-Feb-2013"};
    	usrMap.put("job", buildJobMap(companyNames, companyId, desgination, startDate, endDate, new String[]{"public","public","public"}, jobShareWith, jobNotShareWith));
    	
    	//User Profile has about me
    	prfMap.put("bio", "This is a test");
    	
    	//User profile has profile pic
    	prfMap.put("pic", "test.png");
    	//User profile has cover photo
    	prfMap.put("coverpic", "test.png");
    	//User Profile has favorite quote
    	prfMap.put("quote", "This is a test");
    	//User Profile has blog link
    	prfMap.put("blog", "http://test.com");
    	//User Profile has web link
    	prfMap.put("web", "http://test.com");
    	//User Profile has relation ship status
    	prfMap.put("relationshipstatus", buildRelationShipMap("Singel", null, null, "10-12-2014", "public", null, null));
    	
    	//User has a currency
    	usrMap.put("currency", "INR");
    	//User has time zone
    	usrMap.put("timezone", "utc");
    	//User extra info
    	extraMap.put("Createdon", "10-12-2003");
    	extraMap.put("lastmodifiedon", "10-12-2003");
    	extraMap.put("createdip", "127.0.0.1");
    	usrMap.put("extrainfo", extraMap);
    	
    	//User profile has privacy
    	prviacyMap.put("serachable", "Public");
    	prviacyMap.put("friendrequest", "allow");
    	prfMap.put("privacy", prviacyMap);
    	
    	socialprfMap.put("social", prfMap);
    	usrMap.put("profile", socialprfMap);
    	
    	list.add(usrMap);
		return list;
	}
	
	/**
	 * 
	 * @param fName
	 * @param lName
	 * @return
	 */
	private Map<String, String> buildNameMap(final String fName, final String lName){
		//user has a name, and name compose of fName and lName, which is public
		Map<String, String> nameMap = new HashMap<String, String>(30);
    	nameMap.put("fname", fName);
    	nameMap.put("lname", lName);
		return nameMap;
	}
	
	private Map<String, Object> buildDateOfBirth(final String date, final String month, final String year, final Map<String, Object> dobPrivacyMap, final Map<String, Object> yearPrivacyMap){
		//user has date of birth, which consist of date, month, year, which may be public or may not
    	Map<String, Object> dobMap = new HashMap<String, Object>(4);
    	dobMap.put("date", date);
    	dobMap.put("month", month);
    	dobMap.put("year", year);
    	Map<String, Object> dobPrivacy = new HashMap<String, Object>(2);
    	dobPrivacy.put("dateprivacy", dobPrivacyMap);
    	dobPrivacy.put("yearprivacy", yearPrivacyMap);
    	
    	dobMap.put("privacy", dobPrivacy);
		return dobMap;
	}
	
	/**
	 * <p>Builds the privacy details for the user.
	 * @param privacyName
	 * @param shareWith
	 * @param notshareWith
	 * @return
	 */
	private Map<String, Object> buildPrivacy(final String privacyName, final String[] shareWith, final String[] notshareWith){
		Map<String, Object> dobprivacyMap = new HashMap<String, Object>(2);
    	dobprivacyMap.put("privacy", privacyName);
    	
    	Map<String, Object> customMap = new HashMap<String, Object>(2);
    	
    	if(shareWith != null){
    		customMap.put("sharewith", shareWith);
    	}
    	if(notshareWith != null){
    		customMap.put("notsahrewith", notshareWith);
    	}
    	
    	if(customMap.size() > 0){
    		dobprivacyMap.put("custom", customMap);
    	}
    	
		return dobprivacyMap;
	}
	
	/**
	 * <p>Builds the religion map
	 * @param religionName
	 * @param religionId
	 * @param privacyName
	 * @param shareWith
	 * @param notshareWith
	 * @return
	 */
	private Map<String, Object> buildReligionMap(final String religionName, final String religionId, final String privacyName, final String[] shareWith, final String[] notshareWith){
		Map<String, Object> religionMap = new HashMap<String, Object>(2);
    	religionMap.put("religion", religionName);
    	religionMap.put("religionid", religionId);
    	religionMap.put("privacy", buildPrivacy(privacyName, shareWith, notshareWith));
    	return religionMap; 
	}
	
	/**
	 * <p>Builds the political map
	 * @param politicalviewName
	 * @param politicalviewId
	 * @param privacyName
	 * @param shareWith
	 * @param notshareWith
	 * @return
	 */
	private Map<String, Object> buildPoliticalMap(final String politicalviewName, final String politicalviewId, final String privacyName, final String[] shareWith, final String[] notshareWith){
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("politicalview", politicalviewName);
		map.put("politicalviewid", politicalviewId);
		map.put("privacy", buildPrivacy(privacyName, shareWith, notshareWith));
    	return map; 
	}
	
	/**
	 * <p>Builds the political map
	 * @param bloodGroup
	 * @param bloodGroupId
	 * @param privacyName
	 * @param shareWith
	 * @param notshareWith
	 * @return
	 */
	private Map<String, Object> buildbloodMap(final String bloodGroup, final String bloodGroupId, final String privacyName, final String[] shareWith, final String[] notshareWith){
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("bloodgroup", bloodGroup);
		map.put("bloodgroupid", bloodGroupId);
		map.put("privacy", buildPrivacy(privacyName, shareWith, notshareWith));
    	return map; 
	}
	
	/**
	 * <p>Builds the User Known Language
	 * @param language
	 * @param languageId
	 * @param privacyName
	 * @param shareWith
	 * @param notshareWith
	 * @return
	 */
	private Map<String, Object> buildknownLanguageMap(final String[] language, final String[] languageId, final String privacyName, final String[] shareWith, final String[] notshareWith){
		Map<String, Object> langmap = new HashMap<String, Object>(2);
		List<Map<String, Object>> langs = new ArrayList<Map<String, Object>>();
		for(int i = 0 ; i < language.length - 1 ; i++){
			Map<String, Object> map = new HashMap<String, Object>(2);
			map.put("language", language[i]);
			map.put("langid", languageId[i]);
			langs.add(map);
		}
		langmap.put("langs", langs);
		langmap.put("privacy", buildPrivacy(privacyName, shareWith, notshareWith));
		
    	return langmap; 
	}
	
	/**
	 * <p>Build places
	 * @param place
	 * @param placeId
	 * @param placeType
	 * @param privacyName
	 * @param shareWith
	 * @param notshareWith
	 * @return
	 */
	private List<Map<String, Object>> buildPlacesMap(final String[] place, final String[] placeId, final String[] placeType, final String[] privacyName, final List<String[]> shareWith, final List<String[]> notshareWith){
		List<Map<String, Object>> places = new ArrayList<Map<String, Object>>();
		for(int i = 0 ; i < place.length - 1 ; i++){
			Map<String, Object> map = new HashMap<String, Object>(2);
			map.put("place", place[i]);
			map.put("placeid", placeId[i]);
			map.put("type", placeType[i]);
			map.put("privacy", buildPrivacy(privacyName[i], shareWith.get(i), notshareWith.get(i)));
			places.add(map);
		}
    	return places; 
	}

	/**
	 * <p>Build Jobs
	 * @param companyName
	 * @param companyId
	 * @param desgination
	 * @param startDate
	 * @param endDate
	 * @param privacyName
	 * @param shareWith
	 * @param notshareWith
	 * @return
	 */
	private List<Map<String, Object>> buildJobMap(final String[] companyName, final String[] companyId, final String[] desgination,
			final String[] startDate, final String[] endDate, final String[] privacyName, final List<String[]> shareWith, final List<String[]> notshareWith){
		
		List<Map<String, Object>> jobs = new ArrayList<Map<String, Object>>();
		for(int i = 0 ; i < companyName.length - 1 ; i++){
			Map<String, Object> map = new HashMap<String, Object>(2);
			map.put("company", companyName[i]);
			map.put("companyid", companyId[i]);
			map.put("desgination", desgination[i]);
			map.put("startdate", startDate[i]);
			if(endDate[i] != null){
				map.put("enddate", desgination[i]);
			}
			map.put("privacy", buildPrivacy(privacyName[i], shareWith.get(i), notshareWith.get(i)));
			jobs.add(map);
		}
    	return jobs; 
	}

	/**
	 * <p>Build Relation Ship Map
	 * @param relation
	 * @param withName
	 * @param withId
	 * @param since
	 * @param privacyName
	 * @param shareWith
	 * @param notshareWith
	 * @return
	 */
	private Map<String, Object> buildRelationShipMap(final String relation, final String withName, final String withId, final String since , final String privacyName, final String[] shareWith, final String[] notshareWith){
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("relation", relation);
		if(withName != null){
			Map<String, Object> withmap = new HashMap<String, Object>(2);
			withmap.put("prfid", withId);
			withmap.put("name", withName);
			map.put("with", withmap);
		}
		map.put("since", since);
		map.put("privacy", buildPrivacy(privacyName, shareWith, notshareWith));
    	return map; 
	}
	
	/**
	 * <p>Build Family
	 * @param with
	 * @param withId
	 * @param relation
	 * @param privacyName
	 * @param shareWith
	 * @param notshareWith
	 * @return
	 */
	private List<Map<String, Object>> buildFamilyMap(final String[] with, final String[] withId, final String[] relation, final String[] privacyName, final List<String[]> shareWith, final List<String[]> notshareWith){
		
		List<Map<String, Object>> family = new ArrayList<Map<String, Object>>();
		for(int i = 0 ; i < with.length - 1 ; i++){
			Map<String, Object> map = new HashMap<String, Object>(2);
			map.put("prfid", withId[i]);
			map.put("name", with[i]);
			map.put("relation", relation[i]);
			map.put("privacy", buildPrivacy(privacyName[i], shareWith.get(i), notshareWith.get(i)));
			family.add(map);
		}
    	return family;
	}

}
