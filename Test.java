package tin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.auth.oauth2.UserCredentials;

import tin.Master;

import com.google.ads.googleads.v14.errors.*;

public class Test
{
	public static void main(String[] args) throws Throwable
	{
		// Prod
		//String client_id = "220762967677-el0tahpq23rkeui2cf2v43sf6urgbvc4.apps.googleusercontent.com";
		//String refresh_token = "1//04xbgm_PEr_2cCgYIARAAGAQSNwF-L9IrU-IgrBT8VcSjM-3JNYk6nwR8V_IC-suPyt5LVNWk06ou3qp8eDioxouD86uG6UJYeV0";
		//String client_secret = "Swb-YoCuf-wj6cfh8iX-XQgp";
		//String developer_token = "-OwAmqcVYBHEK6SyhOACgQ";
		
		//long manager_idl = 8067322687L;
		//String manager_id = "8067322687L";

		// Test
		String client_id = "827433072313-0kn0ggcuh2bd44tq947qin2al6c0apcu.apps.googleusercontent.com";
		String refresh_token = "1//03hKaukGm3i5ICgYIARAAGAMSNwF-L9IrLQD5P3NM-PuIyg7MZ7UJdDwUtMgBCgAGc1_fyNYtEq1L92-Ub5MGcOwF1yVOHqLph_s";
		String client_secret = "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";
		String developer_token = "Iis_UWnU9WwEazLPbUntsQ";
		
		long manager_idl = 6783512896L;
		String manager_id = "6783512896";
		
		UserCredentials credentials = UserCredentials.newBuilder()
										.setClientId(client_id)
										.setClientSecret(client_secret)
										.setRefreshToken(refresh_token).build();

		GoogleAdsClient googleAdsClient = GoogleAdsClient.newBuilder()
											.setLoginCustomerId(manager_idl)
											.setCredentials(credentials)
											.setDeveloperToken(developer_token)
											.build();
		
		//4324346291
		//System.out.println(Master.createAccount(googleAdsClient, "PPC Test 06-09-2023", "USD", "America/New_York", manager_id));
		
		//String[] weekDays = {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY"};
		//String[] hourMinutes = {"0","0","16","45"};
		//20529721172
		//System.out.println(Master.addCampaign_Seller(googleAdsClient, "4324346291", "PRESENCE", "UNSPECIFIED", "Search", true, true, true, false, "", true, "", "Manual CPC", "", "PPC Test Campaign 06-09-2023", "", "20.5", "Standard", "PAUSED", weekDays, hourMinutes, "5"));
		//20533906933
		//System.out.println(Master.addCampaign(googleAdsClient, "4324346291", "PRESENCE", "UNSPECIFIED", "Search", true, true, true, false, "", true, "", "Manual CPC", "", "PPC Test Campaign #2 06-09-2023", "", "20.5", "Standard", "PAUSED", weekDays, hourMinutes, "5"));

		//System.out.println(Master.updateLocation(googleAdsClient, "4324346291", "20533906933", "9057150"));

		//System.out.println(Master.Negativelocationfunction(googleAdsClient, "4324346291", "20533906933", "2124"));

		//System.out.println(Master.updateLanguage(googleAdsClient, "4324346291", "20533906933", "1000"));

		//99413851763
		//System.out.println(Master.sitelinks_asset_based(googleAdsClient, "4324346291", "Test Sitelink;Description1;Description2;https://google.com/;ENABLED","20533906933"));

		//System.out.println(Master.Callout_asset_based(googleAdsClient, "4324346291", "20533906933", "Test1 Callout;ENABLED;Description1-2;Description2-2;https://google.com/;ENABLED"));

		//System.out.println(Master.StructuredSnippets_asset_based(googleAdsClient, "4324346291", "20533906933", "Service catalog;Short Sale,HOA Fees,FHA Loan,VA Loan,Cash;ENABLED"));

		//21897973050
		//System.out.println(Master.create_labels(googleAdsClient, "4324346291", "Test Label 2"));

		//154912419564
		//System.out.println(Master.addAdGroup(googleAdsClient, "4324346291", "20533906933;Test Adgroup;ENABLED;4;OPTIMIZE;1"));

		//153626906135
		//System.out.println(Master.addAdGroup_Seller(googleAdsClient, "4324346291", "20533906933;Test Adgroup Seller;ENABLED;4;OPTIMIZE;1"));

		//298940615457
		//System.out.println(Master.addKeywords(googleAdsClient, "4324346291", "153626906135;1;Test Keyword;;EXACT;ENABLED"));

		//System.out.println(Master.add_keyword_labels(googleAdsClient, "4324346291", "153626906135;298940615457;21897973050;1"));

		//298940615457
		//System.out.println(Master.Negativekeywordfunction_Campaign(googleAdsClient, "4324346291", "20533906933;Test Keyword;EXACT;1"));

		//System.out.println(Master.sync_adgroup_status_new(googleAdsClient, "4324346291", "153626906135"));

		//System.out.println(Master.sync_keyword_status(googleAdsClient, "4324346291", "153626906135;298940615457"));

		//System.out.println(Master.sync_keyword_label(googleAdsClient, "4324346291", "153626906135;298940615457"));

		System.out.println(Master.getaccount_labels(googleAdsClient, "4324346291"));
		
		//Master.delete_labels(googleAdsClient, "4324346291", "21891469603");

		//System.out.println(Master.addResponsiveSearchAds(googleAdsClient, "4324346291", "153626906135;H1;H2;H3;H4;H5;H6;H7;H8;H9;H10;H11;H12;H13;H14;H15;D1;D2;D3;D4;P1;P2;https://google.com/;ENABLED;1"));
		
		//Long[] cid = {4324346291L};
		//String path = "C:\\Users\\NisarAhmad\\eclipse-workspace\\Reporting_Files\\";
		//Master.sync_eta(googleAdsClient, cid, path, "");

		//Long[] cid = {4324346291L};
		//String path = "C:\\Users\\NisarAhmad\\eclipse-workspace\\Reporting_Files\\";
		//Master.sync_rsa(googleAdsClient, cid, path, "");

		//7098299355
		//Master x = new Master();
		//System.out.println(x.createAssetSet_dynamic(googleAdsClient, 4324346291L, "1;Test PageFeed"));
		
		//99561074491
		//System.out.println(Master.createAssets_dynamic(googleAdsClient, "4324346291", "Test Label;1;https://thekleinteamnj.com"));

		//Master x = new Master();
		//System.out.println(x.addAssetsToAssetSet_dynamic(googleAdsClient, "customers/4324346291/assetSets/7098299355", 4324346291L, "customers/4324346291/assets/99561074491;1"));
		
		//Master x = new Master();
		//x.linkAssetSetToCampaign(googleAdsClient, "customers/4324346291/assetSets/7098299355", 4324346291L, 20533906933L);
		
		//Master.updateCampaign(googleAdsClient, 4324346291L, 20533906933L, "thekleinteamnj.com");
		
		//155771511000
		//System.out.println(Master.addAdGroup_dynamic_test(googleAdsClient, "4324346291", "20533906933;Test Adgroup Dynamic;ENABLED;4;OPTIMIZE;1"));
		
		//2196274466111
		//System.out.println(Master.addWebPageCriteria_dynamic(googleAdsClient, "4324346291", "155771511000;1;https://thekleinteamnj.com/1/"));		
	}
};
