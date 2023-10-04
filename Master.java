package tin;

import java.io.File;
import java.util.List;
import java.util.Arrays;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedWriter;
import java.util.stream.Collectors;
import java.util.concurrent.TimeUnit;

import org.threeten.bp.LocalDateTime;

import com.google.ads.googleads.v14.common.ManualCpc;
import com.google.ads.googleads.v14.common.TargetCpa;
import com.google.ads.googleads.v14.common.AdTextAsset;
import com.google.ads.googleads.v14.common.KeywordInfo;
import com.google.ads.googleads.v14.common.TargetSpend;
import com.google.ads.googleads.v14.common.WebpageInfo;
import com.google.ads.googleads.v14.common.CalloutAsset;
import com.google.ads.googleads.v14.common.LanguageInfo;
import com.google.ads.googleads.v14.common.LocationInfo;
import com.google.ads.googleads.v14.common.PageFeedAsset;
import com.google.ads.googleads.v14.common.SitelinkAsset;
import com.google.ads.googleads.v14.common.TargetingSetting;
import com.google.ads.googleads.v14.common.TargetRestriction;
import com.google.ads.googleads.v14.common.ExpandedTextAdInfo;
import com.google.ads.googleads.v14.common.PolicyViolationKey;
import com.google.ads.googleads.v14.common.InteractionTypeInfo;
import com.google.ads.googleads.v14.common.WebpageConditionInfo;
import com.google.ads.googleads.v14.common.ResponsiveSearchAdInfo;
import com.google.ads.googleads.v14.common.StructuredSnippetAsset;
import com.google.ads.googleads.v14.common.ExpandedDynamicSearchAdInfo;

import com.google.ads.googleads.v14.enums.AssetTypeEnum.AssetType;
import com.google.ads.googleads.v14.enums.AdGroupTypeEnum.AdGroupType;
import com.google.ads.googleads.v14.enums.AssetSetTypeEnum.AssetSetType;
import com.google.ads.googleads.v14.enums.BudgetStatusEnum.BudgetStatus;
import com.google.ads.googleads.v14.enums.AdGroupStatusEnum.AdGroupStatus;
import com.google.ads.googleads.v14.enums.CriterionTypeEnum.CriterionType;
import com.google.ads.googleads.v14.enums.AssetFieldTypeEnum.AssetFieldType;
import com.google.ads.googleads.v14.enums.CampaignStatusEnum.CampaignStatus;
import com.google.ads.googleads.v14.enums.AdGroupAdStatusEnum.AdGroupAdStatus;
import com.google.ads.googleads.v14.enums.AssetLinkStatusEnum.AssetLinkStatus;
import com.google.ads.googleads.v14.enums.InteractionTypeEnum.InteractionType;
import com.google.ads.googleads.v14.enums.KeywordMatchTypeEnum.KeywordMatchType;
import com.google.ads.googleads.v14.enums.TargetingDimensionEnum.TargetingDimension;
import com.google.ads.googleads.v14.enums.BiddingStrategyTypeEnum.BiddingStrategyType;
import com.google.ads.googleads.v14.enums.ResponseContentTypeEnum.ResponseContentType;
import com.google.ads.googleads.v14.enums.BudgetDeliveryMethodEnum.BudgetDeliveryMethod;
import com.google.ads.googleads.v14.enums.AdGroupAdRotationModeEnum.AdGroupAdRotationMode;
import com.google.ads.googleads.v14.enums.BiddingStrategyStatusEnum.BiddingStrategyStatus;
import com.google.ads.googleads.v14.enums.NegativeGeoTargetTypeEnum.NegativeGeoTargetType;
import com.google.ads.googleads.v14.enums.PositiveGeoTargetTypeEnum.PositiveGeoTargetType;
import com.google.ads.googleads.v14.enums.AdGroupCriterionStatusEnum.AdGroupCriterionStatus;
import com.google.ads.googleads.v14.enums.AdvertisingChannelTypeEnum.AdvertisingChannelType;
import com.google.ads.googleads.v14.enums.WebpageConditionOperandEnum.WebpageConditionOperand;

import com.google.ads.googleads.v14.resources.Ad;
import com.google.ads.googleads.v14.resources.Asset;
import com.google.ads.googleads.v14.resources.Label;
import com.google.ads.googleads.v14.resources.AdGroup;
import com.google.ads.googleads.v14.resources.AssetSet;
import com.google.ads.googleads.v14.resources.Campaign;
import com.google.ads.googleads.v14.resources.Customer;
import com.google.ads.googleads.v14.resources.AdGroupAd;
import com.google.ads.googleads.v14.resources.AdParameter;
import com.google.ads.googleads.v14.resources.AssetSetAsset;
import com.google.ads.googleads.v14.resources.CampaignAsset;
import com.google.ads.googleads.v14.resources.CampaignBudget;
import com.google.ads.googleads.v14.resources.BiddingStrategy;
import com.google.ads.googleads.v14.resources.AdGroupCriterion;
import com.google.ads.googleads.v14.resources.CampaignAssetSet;
import com.google.ads.googleads.v14.resources.CampaignCriterion;
import com.google.ads.googleads.v14.resources.CampaignBidModifier;
import com.google.ads.googleads.v14.resources.AdGroupCriterionLabel;
import com.google.ads.googleads.v14.resources.Campaign.NetworkSettings;
import com.google.ads.googleads.v14.resources.Campaign.GeoTargetTypeSetting;
import com.google.ads.googleads.v14.resources.Campaign.DynamicSearchAdsSetting;

import com.google.ads.googleads.v14.services.GoogleAdsRow;
import com.google.ads.googleads.v14.services.AssetOperation;
import com.google.ads.googleads.v14.services.LabelOperation;
import com.google.ads.googleads.v14.services.MutateOperation;
import com.google.ads.googleads.v14.services.AdGroupOperation;
import com.google.ads.googleads.v14.services.AssetSetOperation;
import com.google.ads.googleads.v14.services.CampaignOperation;
import com.google.ads.googleads.v14.services.MutateAssetResult;
import com.google.ads.googleads.v14.services.MutateLabelResult;
import com.google.ads.googleads.v14.services.AdGroupAdOperation;
import com.google.ads.googleads.v14.services.AssetServiceClient;
import com.google.ads.googleads.v14.services.LabelServiceClient;
import com.google.ads.googleads.v14.services.MutateAdGroupResult;
import com.google.ads.googleads.v14.services.MutateLabelsRequest;
import com.google.ads.googleads.v14.services.AdGroupServiceClient;
import com.google.ads.googleads.v14.services.AdParameterOperation;
import com.google.ads.googleads.v14.services.MutateAssetSetResult;
import com.google.ads.googleads.v14.services.MutateAssetsResponse;
import com.google.ads.googleads.v14.services.MutateCampaignResult;
import com.google.ads.googleads.v14.services.MutateLabelsResponse;
import com.google.ads.googleads.v14.services.AssetSetServiceClient;
import com.google.ads.googleads.v14.services.CampaignServiceClient;
import com.google.ads.googleads.v14.services.CustomerServiceClient;
import com.google.ads.googleads.v14.services.MutateAdGroupAdResult;
import com.google.ads.googleads.v14.services.AdGroupAdServiceClient;
import com.google.ads.googleads.v14.services.AssetSetAssetOperation;
import com.google.ads.googleads.v14.services.CampaignAssetOperation;
import com.google.ads.googleads.v14.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v14.services.MutateAdGroupsResponse;
import com.google.ads.googleads.v14.services.SearchGoogleAdsRequest;
import com.google.ads.googleads.v14.services.CampaignBudgetOperation;
import com.google.ads.googleads.v14.services.MutateAssetSetsResponse;
import com.google.ads.googleads.v14.services.MutateCampaignsResponse;
import com.google.ads.googleads.v14.services.AdParameterServiceClient;
import com.google.ads.googleads.v14.services.BiddingStrategyOperation;
import com.google.ads.googleads.v14.services.MutateAdGroupAdsResponse;
import com.google.ads.googleads.v14.services.AdGroupCriterionOperation;
import com.google.ads.googleads.v14.services.CampaignAssetSetOperation;
import com.google.ads.googleads.v14.services.AssetSetAssetServiceClient;
import com.google.ads.googleads.v14.services.CampaignAssetServiceClient;
import com.google.ads.googleads.v14.services.CampaignCriterionOperation;
import com.google.ads.googleads.v14.services.CampaignBudgetServiceClient;
import com.google.ads.googleads.v14.services.BiddingStrategyServiceClient;
import com.google.ads.googleads.v14.services.CampaignBidModifierOperation;
import com.google.ads.googleads.v14.services.CreateCustomerClientResponse;
import com.google.ads.googleads.v14.services.MutateAdGroupCriterionResult;
import com.google.ads.googleads.v14.services.MutateCampaignAssetsResponse;
import com.google.ads.googleads.v14.services.SearchGoogleAdsStreamRequest;
import com.google.ads.googleads.v14.services.AdGroupCriterionServiceClient;
import com.google.ads.googleads.v14.services.CampaignAssetSetServiceClient;
import com.google.ads.googleads.v14.services.MutateAdGroupCriteriaResponse;
import com.google.ads.googleads.v14.services.MutateCampaignBudgetsResponse;
import com.google.ads.googleads.v14.services.MutateCampaignCriteriaRequest;
import com.google.ads.googleads.v14.services.MutateCampaignCriterionResult;
import com.google.ads.googleads.v14.services.SearchGoogleAdsStreamResponse;
import com.google.ads.googleads.v14.services.AdGroupCriterionLabelOperation;
import com.google.ads.googleads.v14.services.CampaignCriterionServiceClient;
import com.google.ads.googleads.v14.services.MutateCampaignCriteriaResponse;
import com.google.ads.googleads.v14.services.MutateBiddingStrategiesResponse;
import com.google.ads.googleads.v14.services.MutateCampaignAssetSetsResponse;
import com.google.ads.googleads.v14.services.CampaignBidModifierServiceClient;
import com.google.ads.googleads.v14.services.GoogleAdsServiceClient.SearchPage;
import com.google.ads.googleads.v14.services.MutateAdGroupCriterionLabelResult;
import com.google.ads.googleads.v14.services.MutateCampaignBidModifiersRequest;
import com.google.ads.googleads.v14.services.AdGroupCriterionLabelServiceClient;
import com.google.ads.googleads.v14.services.MutateCampaignBidModifiersResponse;
import com.google.ads.googleads.v14.services.MutateAdGroupCriterionLabelsRequest;
import com.google.ads.googleads.v14.services.MutateAdGroupCriterionLabelsResponse;
import com.google.ads.googleads.v14.services.GoogleAdsServiceClient.SearchPagedResponse;

import com.google.ads.googleads.v14.errors.GoogleAdsError;
import com.google.ads.googleads.v14.errors.GoogleAdsException;
import com.google.ads.googleads.v14.errors.PolicyViolationDetails;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.lib.utils.FieldMasks;
import com.google.ads.googleads.v14.utils.ResourceNames;

import com.google.api.gax.rpc.ServerStream;
import com.google.auth.oauth2.UserCredentials;
import com.google.common.collect.ImmutableList;

public class Master
{
	private static final int PAGE_SIZE = 1_000;
	
	public static GoogleAdsClient setCredentials(String refreshToken, String clientId, String clientSecret, String clientCustomerId, String developerToken) throws Throwable
	{
		// Set user credentials
		UserCredentials credentials = UserCredentials.newBuilder()
				 						.setClientId(clientId)
				 						.setClientSecret(clientSecret)
				 						.setRefreshToken(refreshToken)
				 						.build();
		
		long Id = Long.parseLong(clientCustomerId);
		
		return GoogleAdsClient.newBuilder().setLoginCustomerId(Id).setCredentials(credentials).setDeveloperToken(developerToken).build();
	}
	
	
	
	public static void addParam1(GoogleAdsClient googleAdsClient, String customerId, String data) throws Throwable
	{
		// Creates a list of operation
		List<AdParameterOperation> operations = new ArrayList<>();
		
		// Creates a list of parameters and insert data string after splitting on ~
		List<String> list_of_params = new ArrayList<>();
		list_of_params.addAll(Arrays.asList(data.split("~")));
		
		// Iterate through the list of parameters and get values to process by splitting on ;
		for(int i = 0 ; i < list_of_params.size() ; i++)
		{
			String[] values = list_of_params.get(i).split(";");
			
			Long adGroupId = Long.parseLong(values[0]);
			Long keywordId = Long.parseLong(values[1]);
			String insertionText = values[2];
			
			String keywordResourceName = ResourceNames.adParameter(Long.parseLong(customerId), adGroupId, keywordId, 1);
			
			AdParameter builder = AdParameter.newBuilder()
											 .setResourceName(keywordResourceName)
											 .setInsertionText(insertionText)
											 .build();
			
			// Creates an operation and insert it in a list 
			AdParameterOperation op = AdParameterOperation.newBuilder().setUpdate(builder).setUpdateMask(FieldMasks.allSetFieldsOf(builder)).build();
			operations.add(op);
		}
		AdParameterServiceClient addParamServiceClient = googleAdsClient.getLatestVersion().createAdParameterServiceClient();
		addParamServiceClient.mutateAdParameters(customerId, operations);
		try
		{
			addParamServiceClient.shutdown();
			addParamServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			addParamServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
	}
	
	public static void addParam2(GoogleAdsClient googleAdsClient, String customerId, String data) throws Throwable
	{
		// Creates a list of operation
		List<AdParameterOperation> operations = new ArrayList<>();

		// Creates a list of parameters and insert data string after splitting on ~
		List<String> list_of_params = new ArrayList<>();
		list_of_params.addAll(Arrays.asList(data.split("~")));

		// Iterate through the list of parameters and get values to process by splitting on ;
		for(int i = 0 ; i < list_of_params.size() ; i++)
		{
			String[] values = list_of_params.get(i).split(";");
			
			Long adGroupId = Long.parseLong(values[0]);
			Long keywordId = Long.parseLong(values[1]);
			String insertionText = values[2];
			
			String keywordResourceName = ResourceNames.adParameter(Long.parseLong(customerId), adGroupId, keywordId, 2);
			
			AdParameter builder = AdParameter.newBuilder()
											 .setResourceName(keywordResourceName)
											 .setInsertionText(insertionText)
											 .build();
 
			AdParameterOperation op = AdParameterOperation.newBuilder().setUpdate(builder).setUpdateMask(FieldMasks.allSetFieldsOf(builder)).build();
			operations.add(op);
		}
	
		// Mutates the list of operations
		AdParameterServiceClient addParamServiceClient = googleAdsClient.getLatestVersion().createAdParameterServiceClient();
		addParamServiceClient.mutateAdParameters(customerId, operations);
		try
		{
			addParamServiceClient.shutdown();
			addParamServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			addParamServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
	}
	
	public static String updateKeywordStatus(GoogleAdsClient googleAdsClient, String customerId, String data) throws Throwable
	{
		// Creates a list of operations
		List<AdGroupCriterionOperation> operations = new ArrayList<>();
		
		// Creates a list of keyword status after splitting on ~
		List<String> list_of_keyword_status = new ArrayList<>();
		list_of_keyword_status.addAll(Arrays.asList(data.split("~")));
		
		String output = "";
		
		AdGroupCriterionServiceClient adGroupCriterionServiceClient = googleAdsClient.getLatestVersion().createAdGroupCriterionServiceClient();
		List<String> tableIds = new ArrayList<>(); 
		

		// Iterate through the list of parameters and get values to process by splitting on ;
		for(int i = 0 ; i < list_of_keyword_status.size() ; i++)
		{
			String[] values = list_of_keyword_status.get(i).split(";");
			
			Long adGroupId = Long.parseLong(values[0]);
			Long keywordId = Long.parseLong(values[1]);
			String status = values[2];
			String tableId = values[3];
			tableIds.add(tableId);
			
			// Creates adGroupCriterion 
			AdGroupCriterion adGroupCriterion = AdGroupCriterion.newBuilder()
												.setResourceName(ResourceNames.adGroupCriterion(Long.parseLong(customerId), adGroupId, keywordId))
												.setStatus(AdGroupCriterionStatus.valueOf(status))
												.build();
			
			// Creates an operation and add it in the list of operations
			AdGroupCriterionOperation operation = AdGroupCriterionOperation.newBuilder()
													.setUpdate(adGroupCriterion)
													.setUpdateMask(FieldMasks.allSetFieldsOf(adGroupCriterion))
													.build();
			
			operations.add(operation);
		}
	
		// Mutate the operation list and store the response in a variable
		MutateAdGroupCriteriaResponse response = adGroupCriterionServiceClient.mutateAdGroupCriteria(customerId, operations);
			
		// Iterate through the results list and generate an output string
		int index = 0;
		for(@SuppressWarnings("unused") MutateAdGroupCriterionResult mutateAdGroupCriterionResult : response.getResultsList())
		{
			output += tableIds.get(index);
			output += "~";
			index++;
		}
		output = output.substring(0, output.length() - 1);
		
		return output;
	}
	
	public static String geteta(GoogleAdsClient googleAdsClient, String customerId, String adGroupId) throws Throwable
	{
		GoogleAdsServiceClient googleAdsServiceClient = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient();
	
		// Query to fetch the required fields
		String searchQuery = "SELECT customer.descriptive_name, campaign.id, ad_group.id, ad_group.name, campaign.name,"
								+ "ad_group_ad.ad.id, ad_group_ad.ad.expanded_text_ad.headline_part1, ad_group_ad.ad.expanded_text_ad.headline_part2,"
								+ "ad_group_ad.ad.expanded_text_ad.headline_part3, ad_group_ad.ad.expanded_text_ad.description,"
								+ "ad_group_ad.ad.expanded_text_ad.description2, ad_group_ad.ad.final_urls,"
								+ "ad_group_ad.ad.expanded_text_ad.path1, ad_group_ad.ad.expanded_text_ad.path2,"
								+ "ad_group_ad.status, ad_group_ad.ad.type FROM ad_group_ad WHERE"
								+ " ad_group_ad.ad.type = 'EXPANDED_TEXT_AD' AND ad_group_ad.status in ('ENABLED','PAUSED')";
		
		searchQuery += String.format(" AND ad_group.id = '%s'", adGroupId);
		
		// Creates a request that will retrieve all ads using pages of the specified page size.
		SearchGoogleAdsRequest request = SearchGoogleAdsRequest.newBuilder()
											.setCustomerId(customerId)
											.setPageSize(PAGE_SIZE)
											.setQuery(searchQuery)
											.build();
		// Issues the search request.
		SearchPagedResponse searchPagedResponse = googleAdsServiceClient.search(request);
		
		// Iterates over all rows in all pages and prints the requested field values for the ad in each row.
		String output = "";
		
		// Iterate through the result and create an output string containing the ~ separated records with ; separated values
		for(GoogleAdsRow googleAdsRow : searchPagedResponse.iterateAll())
		{
			Ad ad = googleAdsRow.getAdGroupAd().getAd();
			
			ExpandedTextAdInfo expandedTextAdInfo = ad.getExpandedTextAd();
			
			output = output + adGroupId.toString() 
							+ ";" + ad.getId()
							+ ";" + googleAdsRow.getAdGroupAd().getStatus()
							+ ";" + expandedTextAdInfo.getHeadlinePart1()
							+ ";" + expandedTextAdInfo.getHeadlinePart2()
							+ ";" + expandedTextAdInfo.getHeadlinePart3()
							+ ";" + expandedTextAdInfo.getDescription()
							+ ";" + expandedTextAdInfo.getDescription2()
							+ ";" + expandedTextAdInfo.getPath1()
							+ ";" + expandedTextAdInfo.getPath2()
							+ ";" + googleAdsRow.getAdGroupAd().getAd().getFinalUrls(0)
							+ "~";
		}
		output = output.substring(0, output.length() - 1);
		
		return output;
	}
	
	public static String getrsa(GoogleAdsClient googleAdsClient, String customerId, String adGroupId) throws Throwable
	{
		GoogleAdsServiceClient googleAdsServiceClient =
		googleAdsClient.getLatestVersion().createGoogleAdsServiceClient();
		StringBuilder str = new StringBuilder();
		
		// Query to fetch required fields from GoogleAds
		String searchQuery = "SELECT customer.descriptive_name, ad_group.id, campaign.id, ad_group.name, campaign.name,"
								+ "ad_group_ad.ad.id, ad_group_ad.ad.responsive_search_ad.headlines,"
								+ "ad_group_ad.ad.responsive_search_ad.descriptions, ad_group_ad.ad.responsive_search_ad.path1,"
								+ "ad_group_ad.ad.responsive_search_ad.path2, ad_group_ad.ad.final_urls, ad_group_ad.status, ad_group_ad.ad.type "
								+ "FROM ad_group_ad "
								+ "WHERE ad_group_ad.ad.type = 'RESPONSIVE_SEARCH_AD' AND ad_group_ad.status in ('ENABLED','PAUSED')";
		
		searchQuery += String.format(" AND ad_group.id = '%s'", adGroupId);
		
		// Creates a request of the search query
		SearchGoogleAdsRequest request = SearchGoogleAdsRequest.newBuilder()
											.setCustomerId(customerId)
											.setPageSize(PAGE_SIZE)
											.setQuery(searchQuery)
											.build();
		// Iterates over all rows in all pages and prints the requested field values for the ad in each row.
		SearchPagedResponse searchPagedResponse = googleAdsServiceClient.search(request);
		if(searchPagedResponse.getPage().getResponse().getResultsCount() == 0)
		{
			return "";
		}
		
		// Generates an output string
		String output = "";
		for(GoogleAdsRow googleAdsRow : searchPagedResponse.iterateAll())
		{
			AdGroupAd adGroupAd = googleAdsRow.getAdGroupAd();
			Ad ad = adGroupAd.getAd();
			ResponsiveSearchAdInfo responsiveSearchAdInfo = ad.getResponsiveSearchAd();
			
			@SuppressWarnings("rawtypes")
			Iterator s = responsiveSearchAdInfo.getHeadlinesList().listIterator();
			while(s.hasNext())
			{
				String si = s.next().toString();
				int sf = si.indexOf(":");
				int sb = si.indexOf("asset_performance_label");
				sf = sf + 1;
				while(sf<sb)
				{
					str.append(si.charAt(sf));
					sf++;							 
				}
			}
			// Calling the adTextAssetsToStrings method on description list
			String descriptions = adTextAssetsToStrings(responsiveSearchAdInfo.getDescriptionsList());
			
			// Creates a list of descriptions from the string created by adTextAssetsToStrings
			List<String> list_of_description = new ArrayList<>();
			list_of_description.addAll(Arrays.asList(descriptions.split(",")));
			
			output += adGroupId.toString() + ";"
						+ ad.getId() + ";"
						+ googleAdsRow.getAdGroupAd().getStatus() + ";";
			output += str.toString().replace("\n", ";");;
			
			// Append the descriptions in the output string	
			for(String description : list_of_description)
			{
				output += description + ";";
			}
			
			output += responsiveSearchAdInfo.getPath1() + ";";
			output += responsiveSearchAdInfo.getPath2() + ";";
			output += googleAdsRow.getAdGroupAd().getAd().getFinalUrls(0) + "~";
		}
		
		// Removes the ~ on last index
		output = output.substring(0, output.length() - 1);
		
		return output;
	}
	
	private static String adTextAssetsToStrings(List<AdTextAsset> adTextAssets) throws Throwable
	{
		return adTextAssets.stream()
				.map(adTextAsset ->adTextAsset.getText())
				.collect(Collectors.joining(", "));
	}
	
	public static String syncETAStatus(GoogleAdsClient googleAdsClient, long customerId, String data) throws Throwable
	{
		// Separates adGroupId and etaId from the data string
		Long adGroupId = Long.parseLong(data.split("~")[0]);
		Long etaId = Long.parseLong(data.split("~")[1]);
	
		GoogleAdsServiceClient googleAdsServiceClient = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient();
		
		
		// Search Query to extract the status of ETA
		String searchQuery = "SELECT ad_group_ad.status,"
								+ " ad_group_ad.ad.id"
								+ " FROM ad_group_ad"
								+ " WHERE ad_group_ad.ad.type = 'EXPANDED_TEXT_AD' ";
		if(adGroupId != null)
		{
			searchQuery += String.format("AND ad_group.id = %d", adGroupId);
		}
		if(etaId != null)
		{
			searchQuery += String.format(" AND ad_group_ad.ad.id = %d", etaId);
		}
		// Creates a request that will retrieve all ads using pages of the specified page size.
		SearchGoogleAdsRequest request = SearchGoogleAdsRequest.newBuilder()
											.setCustomerId(Long.toString(customerId))
											.setPageSize(PAGE_SIZE)
											.setQuery(searchQuery)
											.build();
		// Issues the search request.
		SearchPagedResponse searchPagedResponse = googleAdsServiceClient.search(request);
		
		// Iterates over all rows in all pages and prints the requested field values for the ad in each row.
		String output = "";
		for(GoogleAdsRow googleAdsRow : searchPagedResponse.iterateAll())
		{
			Ad ad = googleAdsRow.getAdGroupAd().getAd();
			output = output + adGroupId.toString() 
							+ ";" + ad.getId()
							+ ";" + googleAdsRow.getAdGroupAd().getStatus()
							+ "~";
		}
		output = output.substring(0, output.length() - 1);
		
		return output;
	}
	
	public static String syncRSAStatus(GoogleAdsClient googleAdsClient, long customerId, String data) throws Throwable
	{
		Long adGroupId = Long.parseLong(data.split("~")[0]);
		Long rsaId = Long.parseLong(data.split("~")[1]);
		
		GoogleAdsServiceClient googleAdsServiceClient = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient();

		// Search Query to extract the status of RSA
		String searchQuery = "SELECT ad_group.id, ad_group_ad.ad.id, "
								+ "ad_group_ad.status "
								+ "FROM ad_group_ad "
								+ "WHERE ad_group_ad.ad.type = RESPONSIVE_SEARCH_AD ";
		if(adGroupId != null)
		{
			searchQuery += String.format(" AND ad_group.id = %d", adGroupId);
		}
	
		if(rsaId != null)
		{
			searchQuery += String.format(" AND ad_group_ad.ad.id = %d", rsaId);
		}
		SearchGoogleAdsRequest request = SearchGoogleAdsRequest.newBuilder()
											.setCustomerId(Long.toString(customerId))
											.setPageSize(PAGE_SIZE)
											.setQuery(searchQuery)
											.build();

		// Creates a request that will retrieve all ads using pages of the specified page size.
		SearchPagedResponse searchPagedResponse = googleAdsServiceClient.search(request);
		if(searchPagedResponse.getPage().getResponse().getResultsCount() == 0)
		{
			return "";
		}
	
		// Iterates over all rows in all pages and prints the requested field values for the ad in each row.
		String output = "";
		for(GoogleAdsRow googleAdsRow : searchPagedResponse.iterateAll())
		{
			AdGroupAd adGroupAd = googleAdsRow.getAdGroupAd();
			Ad ad = adGroupAd.getAd();
			output += adGroupId.toString() + ";"
					+ ad.getId() + ";"
					+ googleAdsRow.getAdGroupAd().getStatus() + ";";
		}
		output = output.substring(0, output.length() - 1);
		
		return output;
	}
	
	public static long createAccount(GoogleAdsClient googleAdsClient, String Name, String CurrencyCode, String TimeZone, String managerId) throws Throwable
	{
		// Creates a customer with the credentials
		Customer customer = Customer.newBuilder()
								.setDescriptiveName(Name)
								.setCurrencyCode(CurrencyCode)
								.setTimeZone(TimeZone)
								.build();
		
		CustomerServiceClient client = googleAdsClient.getLatestVersion().createCustomerServiceClient();	
		CreateCustomerClientResponse response = client.createCustomerClient(managerId, customer);
		
		return Long.parseLong(response.getResourceName().split("/")[1]);
	}	
	
	public static long addCampaign_Seller(GoogleAdsClient googleAdsClient, String clientCustomerId, String Targetingmethod, String Exclusionmethod, String CampaignType, boolean GoogleSearch, boolean SearchNetwork, boolean ContentNetwork, boolean PartnerSearchNetwork, String StartDate, Boolean EnhancedCPC, String TargetCPA, String BidStrategyType, String BidStrategyName, String CampaignName, String budgetName, String BudgetAmount, String budgetDeliveryMethod, String status, String weekDays[], String[] hourMinutes, String delay) throws Throwable
	{	
		double budgetAmount = Double.parseDouble(BudgetAmount);
		budgetAmount = budgetAmount*1000000;
		BudgetDeliveryMethod deliveryMethod = BudgetDeliveryMethod.valueOf(budgetDeliveryMethod.toUpperCase());
			
		CampaignBudget budget = CampaignBudget.newBuilder()
									.setName(budgetName + System.currentTimeMillis())
									.setStatus(BudgetStatus.ENABLED)
									.setAmountMicros((long) budgetAmount)
									.setExplicitlyShared(false)
									.setDeliveryMethod(deliveryMethod)
									.build();
			
		CampaignBudgetOperation op = CampaignBudgetOperation.newBuilder().setCreate(budget).build();
		CampaignBudgetServiceClient campaignBudgetServiceClient = googleAdsClient.getLatestVersion().createCampaignBudgetServiceClient();	
		MutateCampaignBudgetsResponse response = campaignBudgetServiceClient.mutateCampaignBudgets(clientCustomerId, ImmutableList.of(op));
		String budgetResourceName = response.getResults(0).getResourceName();
	
		NetworkSettings networkSettings = NetworkSettings.newBuilder()
											.setTargetGoogleSearch(GoogleSearch)
											.setTargetSearchNetwork(SearchNetwork)
											.setTargetContentNetwork(ContentNetwork)
											.setTargetPartnerSearchNetwork(PartnerSearchNetwork)
											.build();
		
		 // GeoTargetType settings
		 PositiveGeoTargetType Postarget = PositiveGeoTargetType.valueOf(Targetingmethod);
		 NegativeGeoTargetType Negtarget = NegativeGeoTargetType.valueOf(Exclusionmethod);
		 GeoTargetTypeSetting geoTarget = GeoTargetTypeSetting.newBuilder()
				 							.setPositiveGeoTargetType(Postarget)
				 							.setNegativeGeoTargetType(Negtarget)
				 							.build();
		 
		 // BIDDING STARTEGY
		 String bidname = BidStrategyName.toUpperCase();
		 String bidtype = BidStrategyType.toUpperCase().replace(" ", "_");
		 
		 BiddingStrategyType biddingStrategytype = BiddingStrategyType.valueOf(bidtype.toString());
		 
		 TargetSpend targetSpend = TargetSpend.newBuilder().build();
		 
		 BiddingStrategy biddingstartegy = BiddingStrategy.newBuilder()
				 							.setName(bidname + System.currentTimeMillis())
				 							.setType(biddingStrategytype)
				 							.setStatus(BiddingStrategyStatus.valueOf("ENABLED"))
				 							.setTargetSpend(targetSpend)
				 							.setEffectiveCurrencyCode("USD")
				 							.build();
		 
		 BiddingStrategyOperation bid_strategy = BiddingStrategyOperation.newBuilder().setCreate(biddingstartegy).build();
		 
		 BiddingStrategyServiceClient bidServiceClient = googleAdsClient.getLatestVersion().createBiddingStrategyServiceClient();	
		 MutateBiddingStrategiesResponse bidresponse = bidServiceClient.mutateBiddingStrategies(clientCustomerId, ImmutableList.of(bid_strategy));
		 String bidResourceName = bidresponse.getResults(0).getResourceName();
	
		 //END BIDDING STRATEGY
		 
		 AdvertisingChannelType advertisingchanneltype = AdvertisingChannelType.valueOf(CampaignType.toUpperCase());
		 
		 String startdate = StartDate;
		if(startdate.trim() == null || startdate.trim()== "" )
		{
			LocalDateTime y = LocalDateTime.now();
			startdate = y.toString();
			startdate = startdate.substring(0, 10);
		}
		
		CampaignStatus Campstatus = CampaignStatus.valueOf(status);
		
		ManualCpc BiddingSchemeMcpc = null;
		Campaign campaign = null;
		
		// CAMPAIGN SETTING
		if(bidtype.equals("MANUAL_CPC"))
		{
			BiddingSchemeMcpc = ManualCpc.newBuilder()
									.setEnhancedCpcEnabled(EnhancedCPC)
									.build();
			
			TargetRestriction Tr = TargetRestriction.newBuilder()
										.setTargetingDimensionValue(TargetingDimension.AUDIENCE_VALUE)
										.setBidOnly(true).build();
			
			campaign = Campaign.newBuilder()
							.setName(CampaignName)
							.setStatus(Campstatus)
							.setStartDate(startdate)
							.setCampaignBudget(budgetResourceName)
							.setAdvertisingChannelType(advertisingchanneltype)
							.setNetworkSettings(networkSettings)
							.setGeoTargetTypeSetting(geoTarget)
							.setBiddingStrategy(bidResourceName)
							.setManualCpc(BiddingSchemeMcpc)
							.setTargetingSetting(TargetingSetting.newBuilder()
													.addTargetRestrictions(Tr)
													.build())
							.build();
		}
		else if(bidtype.equals("TARGET_CPA"))
		{	
			double cpa = Double.parseDouble(TargetCPA)*1000000;
			TargetCpa BiddingSchemeEcpc = TargetCpa.newBuilder()
											.setTargetCpaMicros((long) cpa)
											.build();
			
			TargetRestriction Tr = TargetRestriction.newBuilder()
									.setTargetingDimensionValue(TargetingDimension.AUDIENCE_VALUE)
									.setBidOnly(true).build();
			
			campaign = Campaign.newBuilder()
						.setName(CampaignName + System.currentTimeMillis())
						.setStatus(Campstatus)
						.setStartDate(startdate)
						.setCampaignBudget(budgetResourceName)
						.setAdvertisingChannelType(advertisingchanneltype)
						.setNetworkSettings(networkSettings)
						.setGeoTargetTypeSetting(geoTarget)
						.setBiddingStrategy(bidResourceName)
						.setTargetCpa(BiddingSchemeEcpc)
						.setTargetingSetting(TargetingSetting.newBuilder()
												.addTargetRestrictions(Tr)
												.build())
						.build();
		}

		String campaignResourceName = null;
		Long campaign_id = null;
		CampaignOperation Camp_operation = CampaignOperation.newBuilder().setCreate(campaign).build();
		
		List<CampaignOperation> operations = new ArrayList<>();
		operations.add(Camp_operation);
			
		CampaignServiceClient campaignServiceClient = googleAdsClient.getLatestVersion().createCampaignServiceClient(); 
		MutateCampaignsResponse camp_response = campaignServiceClient.mutateCampaigns(clientCustomerId, operations);
		
		for(MutateCampaignResult result : camp_response.getResultsList())
		{
			campaignResourceName = result.getResourceName();
			String[] camp_outputs = campaignResourceName.split("/");
			campaign_id = Long.parseLong(camp_outputs[3]);
		}
		//END CAMPAIGN
		
		//CAMPAIGN BIDMODIFER
		CampaignBidModifier campaignBidModifier = CampaignBidModifier.newBuilder()
													.setCampaign(campaignResourceName)
													// Makes the bid modifier apply to call interactions.
													.setInteractionType(InteractionTypeInfo.newBuilder()
																			.setType(InteractionType.CALLS))
													// Uses the specified bid modifier value.
													.setBidModifier((float) 0.7)
													.build();
		
		CampaignBidModifierOperation op1 =	CampaignBidModifierOperation.newBuilder().setCreate(campaignBidModifier).build();
		MutateCampaignBidModifiersRequest request = MutateCampaignBidModifiersRequest.newBuilder()
														.addOperations(op1)
														.setCustomerId(String.valueOf(clientCustomerId))
														// Specifies that we want to the request to return the mutated object and not just its resource name.
														.setResponseContentType(ResponseContentType.MUTABLE_RESOURCE)
														.build();
		
		CampaignBidModifierServiceClient agcServiceClient = googleAdsClient.getLatestVersion().createCampaignBidModifierServiceClient();
		MutateCampaignBidModifiersResponse response_ = agcServiceClient.mutateCampaignBidModifiers(request);
		CampaignBidModifier mutableResource = response_.getResults(0).getCampaignBidModifier();
		System.out.printf("Created campaign bid modifier with resource_name "
							+ "'%s', criterion ID "
							+ "%d, and bid modifier value "
							+ "%s, under the campaign with "
							+ "resource_name '%s'.%n"
							, mutableResource.getResourceName()
							, mutableResource.getCriterionId()
							, mutableResource.getBidModifier()
							, mutableResource.getCampaign()
						 );
		 
		return campaign_id;	
	}

	public static long addCampaign(GoogleAdsClient googleAdsClient, String clientCustomerId, String Targetingmethod, String Exclusionmethod, String CampaignType, boolean GoogleSearch, boolean SearchNetwork, boolean ContentNetwork, boolean PartnerSearchNetwork, String StartDate, Boolean EnhancedCPC, String TargetCPA, String BidStrategyType, String BidStrategyName, String CampaignName, String budgetName, String BudgetAmount, String budgetDeliveryMethod, String status, String weekDays[], String[] hourMinutes, String delay) throws Throwable
	{	
		// Budget setting for camp
		double budgetAmount = Double.parseDouble(BudgetAmount);
		budgetAmount = budgetAmount*1000000;
		
		BudgetDeliveryMethod deliveryMethod = BudgetDeliveryMethod.valueOf(budgetDeliveryMethod.toUpperCase());
				
		CampaignBudget budget = CampaignBudget.newBuilder()
									.setName(budgetName + System.currentTimeMillis())
									.setStatus(BudgetStatus.ENABLED)
									.setAmountMicros((long) budgetAmount)
									.setExplicitlyShared(false)
									.setDeliveryMethod(deliveryMethod)
									.build();
				
		CampaignBudgetOperation op = CampaignBudgetOperation.newBuilder().setCreate(budget).build();
		CampaignBudgetServiceClient campaignBudgetServiceClient = googleAdsClient.getLatestVersion().createCampaignBudgetServiceClient();	
		
		MutateCampaignBudgetsResponse response = campaignBudgetServiceClient.mutateCampaignBudgets(clientCustomerId, ImmutableList.of(op));
		
		String budgetResourceName = response.getResults(0).getResourceName();
		
		//Networking settings
		NetworkSettings networkSettings = NetworkSettings.newBuilder()
											.setTargetGoogleSearch(GoogleSearch)
											.setTargetSearchNetwork(SearchNetwork)
											.setTargetContentNetwork(ContentNetwork)
											.setTargetPartnerSearchNetwork(PartnerSearchNetwork)
											.build();
			
		// GeoTargetType settings
		PositiveGeoTargetType Postarget = PositiveGeoTargetType.valueOf(Targetingmethod);
		NegativeGeoTargetType Negtarget = NegativeGeoTargetType.valueOf(Exclusionmethod);
			 
		GeoTargetTypeSetting geoTarget = GeoTargetTypeSetting.newBuilder()
											.setPositiveGeoTargetType(Postarget)
											.setNegativeGeoTargetType(Negtarget)
											.build();
			 
		 // BIDDING STARTEGY
		String bidname = BidStrategyName.toUpperCase();
		String bidtype = BidStrategyType.toUpperCase().replace(" ", "_");
			 
		BiddingStrategyType biddingStrategytype = BiddingStrategyType.valueOf(bidtype.toString());
			 
		TargetSpend targetSpend = TargetSpend.newBuilder().build();
			 
		BiddingStrategy biddingstartegy = BiddingStrategy.newBuilder()
					 						.setName(bidname + System.currentTimeMillis())
					 						.setType(biddingStrategytype)
					 						.setStatus(BiddingStrategyStatus.valueOf("ENABLED"))
					 						.setTargetSpend(targetSpend)
					 						.setEffectiveCurrencyCode("USD")
					 						.build();
			 
		BiddingStrategyOperation bid_strategy = BiddingStrategyOperation.newBuilder().setCreate(biddingstartegy).build();
			 
		BiddingStrategyServiceClient bidServiceClient = googleAdsClient.getLatestVersion().createBiddingStrategyServiceClient();	
		MutateBiddingStrategiesResponse bidresponse = bidServiceClient.mutateBiddingStrategies(clientCustomerId, ImmutableList.of(bid_strategy));
		String bidResourceName = bidresponse.getResults(0).getResourceName();
		// END BIDDING STRATEGY
			 
		AdvertisingChannelType advertisingchanneltype = AdvertisingChannelType.valueOf(CampaignType.toUpperCase());
		
		String startdate = StartDate;
		if(startdate.trim() == null || startdate.trim()== "" )
		{
			LocalDateTime y = LocalDateTime.now();
			startdate = y.toString();
			startdate = startdate.substring(0, 10);
		}
		CampaignStatus Campstatus = CampaignStatus.valueOf(status);
			 
		ManualCpc BiddingSchemeMcpc = null;
		Campaign campaign = null;
			
		// CAMPAIGN SETTING
		if(bidtype.equals("MANUAL_CPC"))
		{
			BiddingSchemeMcpc = ManualCpc.newBuilder()
						 			.setEnhancedCpcEnabled(EnhancedCPC)
						 			.build();
				 
			campaign = Campaign.newBuilder()
						.setName(CampaignName)
						.setStatus(Campstatus)
						.setStartDate(startdate)
						.setCampaignBudget(budgetResourceName)
						.setAdvertisingChannelType(advertisingchanneltype)
						.setNetworkSettings(networkSettings)
						.setGeoTargetTypeSetting(geoTarget)
						.setBiddingStrategy(bidResourceName)
						.setManualCpc(BiddingSchemeMcpc)
						.build();
		}
		else if(bidtype.equals("TARGET_CPA"))
		{	
			double cpa = Double.parseDouble(TargetCPA)*1000000;
			TargetCpa BiddingSchemeEcpc = TargetCpa.newBuilder()
											.setTargetCpaMicros((long) cpa)
											.build();
				 
			campaign = Campaign.newBuilder()
						.setName(CampaignName + System.currentTimeMillis())
						.setStatus(Campstatus)
						.setStartDate(startdate)
						.setCampaignBudget(budgetResourceName)
						.setAdvertisingChannelType(advertisingchanneltype)
						.setNetworkSettings(networkSettings)
						.setGeoTargetTypeSetting(geoTarget)
						.setBiddingStrategy(bidResourceName)
						.setTargetCpa(BiddingSchemeEcpc)
						.build();
		}
		String campaignResourceName = null;
		Long campaign_id = null;
		CampaignOperation Camp_operation = CampaignOperation.newBuilder().setCreate(campaign).build();
		List<CampaignOperation> operations = new ArrayList<>();
		operations.add(Camp_operation);
				
		CampaignServiceClient campaignServiceClient = googleAdsClient.getLatestVersion().createCampaignServiceClient(); 
		MutateCampaignsResponse camp_response = campaignServiceClient.mutateCampaigns(clientCustomerId, operations);
			
		for(MutateCampaignResult result : camp_response.getResultsList())
		{
			campaignResourceName = result.getResourceName();
			String[] camp_outputs = campaignResourceName.split("/");
			campaign_id = Long.parseLong(camp_outputs[3]);
		}
		// END CAMPAIGN
			
		// CAMPAIGN BIDMODIFER
		
		CampaignBidModifier campaignBidModifier = CampaignBidModifier.newBuilder()
														.setCampaign(campaignResourceName)
														// Makes the bid modifier apply to call interactions.
														.setInteractionType(InteractionTypeInfo.newBuilder()
																				.setType(InteractionType.CALLS))
														// Uses the specified bid modifier value.
														.setBidModifier((float) 0.7)
														.build();
			
		CampaignBidModifierOperation op1 = CampaignBidModifierOperation.newBuilder()
												.setCreate(campaignBidModifier)
												.build();
		
		MutateCampaignBidModifiersRequest request = MutateCampaignBidModifiersRequest.newBuilder()
														.addOperations(op1)
														.setCustomerId(String.valueOf(clientCustomerId))
														// Specifies that we want to the request to return the mutated object and not just its resource name.
														.setResponseContentType(ResponseContentType.MUTABLE_RESOURCE)
														.build();
		
		CampaignBidModifierServiceClient agcServiceClient = googleAdsClient.getLatestVersion().createCampaignBidModifierServiceClient();
		MutateCampaignBidModifiersResponse response_ = agcServiceClient.mutateCampaignBidModifiers(request);
		
		CampaignBidModifier mutableResource = response_.getResults(0).getCampaignBidModifier();
		System.out.printf("Created campaign bid modifier with resource_name "
				+ "'%s', criterion ID "
				+ "%d, and bid modifier value "
				+ "%s, under the campaign with "
				+ "resource_name '%s'.%n"
				, mutableResource.getResourceName()
				, mutableResource.getCriterionId()
				, mutableResource.getBidModifier()
				, mutableResource.getCampaign()
			 );
		 
		try
		{
			agcServiceClient.shutdown();
			agcServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			agcServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		
		return campaign_id;	
	}
	
	public static boolean updateLocation(GoogleAdsClient googleAdsClient, String customerId, String campaignId, String Location) throws Throwable
	{
		String campaignResourceName = ResourceNames.campaign(Long.parseLong(customerId), Long.parseLong(campaignId));
		
		LocationInfo li = LocationInfo.newBuilder().setGeoTargetConstant("geoTargetConstants/" + Location).build();
		
		CampaignCriterion builder = MutateOperation.newBuilder()
										.getCampaignCriterionOperationBuilder().getCreateBuilder()
										.setCampaign(campaignResourceName)
										.setType(CriterionType.LOCATION)
										.setLocation(li)
										.build();

		CampaignCriterionOperation o = CampaignCriterionOperation.newBuilder()
										.setCreate(builder)
										.build();

		MutateCampaignCriteriaRequest Mutate_Camp_asset_request = MutateCampaignCriteriaRequest.newBuilder()
																		.addOperations(o)
																		.setCustomerId(String.valueOf(customerId))
																		.build();
		CampaignCriterionServiceClient agcServiceClient = googleAdsClient.getLatestVersion().createCampaignCriterionServiceClient();
		agcServiceClient.mutateCampaignCriteria(Mutate_Camp_asset_request);
		try
		{
			agcServiceClient.shutdown();
			agcServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			agcServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		
		return true;
	}

	public static boolean Negativelocationfunction(GoogleAdsClient googleAdsClient, String customerId, String campaignId, String Location) throws Throwable
	{
		String campaignResourceName = ResourceNames.campaign(Long.parseLong(customerId), Long.parseLong(campaignId));
		
		LocationInfo li = LocationInfo.newBuilder().setGeoTargetConstant("geoTargetConstants/" + Location).build();
		
		CampaignCriterion builder = MutateOperation.newBuilder()
										.getCampaignCriterionOperationBuilder().getCreateBuilder()
										.setCampaign(campaignResourceName)
										.setType(CriterionType.LOCATION)
										.setNegative(true)
										.setLocation(li)
										.build();

		CampaignCriterionOperation o = CampaignCriterionOperation.newBuilder().setCreate(builder).build();

		MutateCampaignCriteriaRequest Mutate_Camp_asset_request = MutateCampaignCriteriaRequest.newBuilder()
																	.addOperations(o)
																	.setCustomerId(String.valueOf(customerId))
																	.build();
		
		CampaignCriterionServiceClient agcServiceClient = googleAdsClient.getLatestVersion().createCampaignCriterionServiceClient();
		agcServiceClient.mutateCampaignCriteria(Mutate_Camp_asset_request);
		try
		{
			agcServiceClient.shutdown();
			agcServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			agcServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		
		return true;
	}
	
	public static boolean updateLanguage(GoogleAdsClient googleAdsClient, String customerId, String campaignId, String Lang) throws Throwable
	{
		String campaignResourceName = ResourceNames.campaign(Long.parseLong(customerId), Long.parseLong(campaignId));
		
		LanguageInfo li = LanguageInfo.newBuilder().setLanguageConstant("languageConstants/" + Lang).build();
		
		CampaignCriterion builder = MutateOperation.newBuilder()
										.getCampaignCriterionOperationBuilder().getCreateBuilder()
										.setCampaign(campaignResourceName)
										.setType(CriterionType.LANGUAGE)
										.setLanguage(li)
										.build();

		CampaignCriterionOperation o = CampaignCriterionOperation.newBuilder()
											.setCreate(builder)
											.build();

		MutateCampaignCriteriaRequest Mutate_Camp_asset_request = MutateCampaignCriteriaRequest.newBuilder()
																		.addOperations(o)
																		.setCustomerId(String.valueOf(customerId))
																		.build();
		CampaignCriterionServiceClient agcServiceClient = googleAdsClient.getLatestVersion().createCampaignCriterionServiceClient();
		agcServiceClient.mutateCampaignCriteria(Mutate_Camp_asset_request);
		
		return true;
	}

	public static Boolean sitelinks_asset_based(GoogleAdsClient googleAdsClient, String customerId, String record, String campaignId) throws Throwable
	{	
		//Campaign Resource name is generated 
		String campaignResourceName = ResourceNames.campaign(Long.parseLong(customerId), Long.parseLong(campaignId));
		
		String[] Record_Sitelinks = record.split("~"); //Different Sitelinks seperated by ~
		
		String[] Sitelink;
			
		//For each sitelink 
		for(int i = 0 ; i < Record_Sitelinks.length ; i++)
		{
			Sitelink = Record_Sitelinks[i].split(";");

			SitelinkAsset sitelink_link_item1 = SitelinkAsset.newBuilder()
													.setLinkText(Sitelink[0]) 			
													.setDescription1(Sitelink[1]) 			
													.setDescription2(Sitelink[2])
													.build(); 
				
			Asset asset = Asset.newBuilder()
							.setSitelinkAsset(sitelink_link_item1)
							.addFinalUrls(Sitelink[3])
							.setType(AssetType.SITELINK)
							.build();
			
			
			AssetOperation operation = AssetOperation.newBuilder().setCreate(asset).build();
			
			AssetServiceClient assetServiceClient = googleAdsClient.getLatestVersion().createAssetServiceClient();
			
			// Issues a mutate request to add the asset.
			MutateAssetsResponse response =	assetServiceClient.mutateAssets(customerId, ImmutableList.of(operation));
			
			// Prints the result.
			String resourceName = response.getResultsList().get(0).getResourceName();
			System.out.printf("The image asset with resource name '%s' was created.%n", response.getResults(0).getResourceName());
				
			CampaignAsset campaignAsset = CampaignAsset.newBuilder()
											.setAsset(resourceName)
											.setFieldType(AssetFieldType.SITELINK)
											.setCampaign(campaignResourceName.toString())
											.setStatus(AssetLinkStatus.valueOf(Sitelink[4]))			
											.build();
			
			CampaignAssetOperation operation1 = CampaignAssetOperation.newBuilder()
													.setCreate(campaignAsset)
													.build();

			CampaignAssetServiceClient client1 = googleAdsClient.getLatestVersion().createCampaignAssetServiceClient();
			MutateCampaignAssetsResponse response1 = client1.mutateCampaignAssets(String.valueOf(customerId), ImmutableList.of(operation1));
			System.out.printf("Created campaign asset with resource name '%s' for campaign ID ", response1.getResultsList().get(0).getResourceName(), campaignId);
			try
			{
				assetServiceClient.shutdown();
				assetServiceClient.awaitTermination(2, TimeUnit.SECONDS);
				assetServiceClient.close();
			}
			catch(InterruptedException e)
			{
			}
			try
			{
				client1.shutdown();
				client1.awaitTermination(2, TimeUnit.SECONDS);
				client1.close();
			}
			catch(InterruptedException e)
			{
			}	
		}

		return true;
	}
	
	public static boolean Callout_asset_based(GoogleAdsClient googleAdsClient, String customerId, String campaignId, String record) throws Throwable
	{
		//Campaign Resource Name
		String campaignResourceName = ResourceNames.campaign(Long.parseLong(customerId), Long.parseLong(campaignId));
		
		String[] Callout_datas = record.split("~");
		String[] Callout;
		
		//Iterate and for each callout set feed item 
		for(int i = 0 ; i < Callout_datas.length ; i++)
		{
			Callout = Callout_datas[i].split(";");
			
			CalloutAsset callout_item1 = CalloutAsset.newBuilder()
											.setCalloutText(Callout[0])
											.build();
			
			 Asset asset = Asset.newBuilder()
					 		.setCalloutAsset(callout_item1)
					 		.setType(AssetType.CALLOUT)
					 		.build();
		
			 AssetOperation operation = AssetOperation.newBuilder()
					 					.setCreate(asset)
					 					.build();
		
			 AssetServiceClient assetServiceClient = googleAdsClient.getLatestVersion().createAssetServiceClient();
			
			 // Issues a mutate request to add the asset.
			 MutateAssetsResponse response = assetServiceClient.mutateAssets(customerId, ImmutableList.of(operation));
			 // Prints the result.
			 String resourceName = response.getResultsList().get(0).getResourceName();

			 CampaignAsset campaignAsset = CampaignAsset.newBuilder()
					 						.setAsset(resourceName)
					 						.setFieldType(AssetFieldType.CALLOUT)
					 						.setCampaign(campaignResourceName.toString())
					 						.setStatus(AssetLinkStatus.valueOf(Callout[1]))
					 						.build();
			 CampaignAssetOperation operation1 = CampaignAssetOperation.newBuilder().setCreate(campaignAsset).build();

			 CampaignAssetServiceClient client1 = googleAdsClient.getLatestVersion().createCampaignAssetServiceClient();
			 client1.mutateCampaignAssets(String.valueOf(customerId), ImmutableList.of(operation1));

			 try
			 {
				assetServiceClient.shutdown();
				assetServiceClient.awaitTermination(2, TimeUnit.SECONDS);
				assetServiceClient.close();
			 }
			 catch(InterruptedException e)
			 {
			 }
			 try
			 {
				client1.shutdown();
				client1.awaitTermination(2, TimeUnit.SECONDS);
				client1.close();
			 }
			 catch(InterruptedException e)
			 {
			 }		
		
		}
		
		return true;		
	}
	
	public static boolean StructuredSnippets_asset_based(GoogleAdsClient googleAdsClient, String customerId, String campaignId, String record) throws Throwable
	{	
		String campaignResourceName = ResourceNames.campaign(Long.parseLong(customerId), Long.parseLong(campaignId));
		
		String[] SS_datas = record.split("~");
		String[] SSnippet;
		for(int i = 0 ; i < SS_datas.length ; i++)
		{
			SSnippet = SS_datas[i].split(";");
			
			Iterable<String> iterable = Arrays.asList(SSnippet[1].split(","));
			
			StructuredSnippetAsset snippet_item1 = StructuredSnippetAsset.newBuilder()
													.setHeader(SSnippet[0]).addAllValues(iterable)
													.build();
			
			 Asset asset = Asset.newBuilder()
					 		.setStructuredSnippetAsset(snippet_item1)
					 		.setType(AssetType.STRUCTURED_SNIPPET)
					 		.build();
		
			 AssetOperation operation = AssetOperation.newBuilder()
					 						.setCreate(asset)
					 						.build();
		
			 AssetServiceClient assetServiceClient = googleAdsClient.getLatestVersion().createAssetServiceClient();
			
			 // Issues a mutate request to add the asset.
			MutateAssetsResponse response = assetServiceClient.mutateAssets(customerId, ImmutableList.of(operation));

			String resourceName = response.getResultsList().get(0).getResourceName();
			 
			CampaignAsset campaignAsset = CampaignAsset.newBuilder()
											.setAsset(resourceName)
											.setFieldType(AssetFieldType.STRUCTURED_SNIPPET)
											.setCampaign(campaignResourceName.toString())
											.setStatus(AssetLinkStatus.valueOf(SSnippet[2].toUpperCase()))
											.build();
			CampaignAssetOperation operation1 = CampaignAssetOperation.newBuilder()
													.setCreate(campaignAsset)
													.build();

			CampaignAssetServiceClient client1 = googleAdsClient.getLatestVersion().createCampaignAssetServiceClient();
			client1.mutateCampaignAssets(String.valueOf(customerId), ImmutableList.of(operation1));
	
			try
			{
				assetServiceClient.shutdown();
				assetServiceClient.awaitTermination(2, TimeUnit.SECONDS);
				assetServiceClient.close();
			}
			catch(InterruptedException e)
			{
			}
			try
			{
				client1.shutdown();
				client1.awaitTermination(2, TimeUnit.SECONDS);
				client1.close();
			}
			catch(InterruptedException e)
			{
			}	
		}
			
		return true;		
	}
	
	public static String create_labels(GoogleAdsClient googleAdsClient, String customerId, String Data) throws Throwable
	{ 
		String[] Labels = Data.split("~");
		String data = "";
		String Labelnames = null + ";";
		List<LabelOperation> operations = new ArrayList<>();
		
		for(int i = 0 ; i < Labels.length ; i++) 
		{
			Label Label_account = Label.newBuilder().setName(Labels[i]).build();
			Labelnames = Labelnames + Labels[i] + ";";
			LabelOperation las = LabelOperation.newBuilder().setCreate(Label_account).build();
			operations.add(las);
		}
		
		LabelServiceClient labelseviceclient = googleAdsClient.getLatestVersion().createLabelServiceClient();
		
		MutateLabelsResponse responce1 = labelseviceclient.mutateLabels(customerId, operations);
		
		int a = 1;
		for(MutateLabelResult result : responce1.getResultsList())
		{
			String [] out = null;
			String [] out1 = null;
			out1 = Labelnames.split(";");
			out = result.getResourceName().split("/");
			data = data + out[3] + ";";
			data = data + out1[a]+ "~";
			a++;
		}
		try
		{
			 labelseviceclient.shutdown();
			 labelseviceclient.awaitTermination(5, TimeUnit.SECONDS);
			 labelseviceclient.close();
		}
		catch(InterruptedException e)
		{
		}
		data = data.substring(0, data.length() - 1);
		
		return data;
	}
	
	public static String addAdGroup(GoogleAdsClient googleAdsClient, String customerId, String data)
	{
		List<String> list_of_groups = new ArrayList<>();
		list_of_groups.addAll(Arrays.asList(data.split("~")));
		
		String output = "";
		long[] table_ids = new long[list_of_groups.size()];
		List<AdGroupOperation> operations = new ArrayList<>();
		
		for(int i = 0 ; i < list_of_groups.size() ; i++)
		{
			String[] values = list_of_groups.get(i).split(";");
			String campaignResourceName = ResourceNames.campaign( Long.parseLong(customerId), Long.parseLong(values[0]));
			String adw_adgroup_name = values[1];
			String status = values[2];
			float max_cpc1 = Float.parseFloat(values[3]);
			
			long max_cpc =(long) (Math.round(max_cpc1*1000)* 1000);
			String rotation = values[4];
			String table_id = values[5];
			
			table_ids[i] = Long.parseLong(table_id);
			AdGroup adGroup = AdGroup.newBuilder()
									 .setName(adw_adgroup_name)
									 .setStatus(AdGroupStatus.valueOf(status))
									 .setCampaign(campaignResourceName)
									 .setCpcBidMicros(max_cpc)
									 .setAdRotationMode(AdGroupAdRotationMode.valueOf(rotation))
									 .build();
			
			AdGroupOperation Camp_operation = AdGroupOperation.newBuilder().setCreate(adGroup).build();
			operations.add(Camp_operation);		
		}
		
		AdGroupServiceClient adGroupServiceClient = googleAdsClient.getLatestVersion().createAdGroupServiceClient(); 
		MutateAdGroupsResponse adGroup_response = adGroupServiceClient.mutateAdGroups(String.valueOf(customerId), operations);
		int index = 0;
		
		for(MutateAdGroupResult result : adGroup_response.getResultsList())
		{
			output += table_ids[index];
			output += ";";
			output += result.getResourceName().split("/")[3];
			output += "~";
			index += 1;
		}
		try
		{
			adGroupServiceClient.shutdown();
			adGroupServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			adGroupServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		output = output.substring(0, output.length() - 1);
		
		return output;
	}
	
	public static String addAdGroup_Seller(GoogleAdsClient googleAdsClient, String customerId, String data)
	{
		List<String> list_of_groups = new ArrayList<>();
		list_of_groups.addAll(Arrays.asList(data.split("~")));
		
		String output = "";
		long[] table_ids = new long[list_of_groups.size()];
		List<AdGroupOperation> operations = new ArrayList<>();
		
		for(int i = 0 ; i < list_of_groups.size() ; i++)
		{
			String[] values = list_of_groups.get(i).split(";");
			String campaignResourceName = ResourceNames.campaign( Long.parseLong(customerId), Long.parseLong(values[0]));
			String adw_adgroup_name = values[1];
			String status = values[2];
			
			float max_cpc1 = Float.parseFloat(values[3]);
			long max_cpc =(long) (Math.round(max_cpc1*1000)* 1000);
			
			//String rotation = values[4];
			String table_id = values[5];
			table_ids[i] = Long.parseLong(table_id);
			
			TargetRestriction target_obs = TargetRestriction.newBuilder().setTargetingDimension(TargetingDimension.AUDIENCE).setBidOnly(true)
								.build();
			
			AdGroup adGroup = AdGroup.newBuilder()
								.setName(adw_adgroup_name)
								.setStatus(AdGroupStatus.valueOf(status))
								.setCampaign(campaignResourceName)
								.setCpcBidMicros(max_cpc)
								.setTargetingSetting(TargetingSetting.newBuilder()
								.addTargetRestrictions(target_obs).build())
								.build();
								//.setAdRotationMode(AdGroupAdRotationMode.valueOf(rotation))
									 
			AdGroupOperation Camp_operation = AdGroupOperation.newBuilder()
												.setCreate(adGroup)
												.build();
			operations.add(Camp_operation);
				
		}
		
		AdGroupServiceClient adGroupServiceClient = googleAdsClient.getLatestVersion().createAdGroupServiceClient(); 
		MutateAdGroupsResponse adGroup_response = adGroupServiceClient.mutateAdGroups(String.valueOf(customerId), operations);
		
		int index = 0;
		for(MutateAdGroupResult result : adGroup_response.getResultsList())
		{
			output += table_ids[index];
			output += ";";
			output += result.getResourceName().split("/")[3];
			output += "~";
			index += 1;
		}
		output = output.substring(0, output.length() - 1);
		
		return output;
	}
	
	public static String addKeywords(GoogleAdsClient googleAdsClient, String customerId, String data)
	{
		List<AdGroupCriterionOperation> operations = new ArrayList<>();
		List<String> list_of_keywords = new ArrayList<>();
		list_of_keywords.addAll(Arrays.asList(data.split("~")));
		String output = "";
		long[] keyword_ids = new long[list_of_keywords.size()];
		
		for(int i = 0 ; i < list_of_keywords.size() ; i++)
		{
			String[] values = list_of_keywords.get(i).split(";");
			String adGroupId = values[0];
			String keywordId = values[1];
			String keywordText = values[2];
			String finalUrl = values[3];
			String matchType = values[4];
			String status = values[5];
			
			keyword_ids[i] = Long.parseLong(keywordId);
		
			KeywordInfo keywordInfo = KeywordInfo.newBuilder()
										.setText(keywordText)
										.setMatchType(KeywordMatchType.valueOf((matchType).toUpperCase()))
										.build();
	
			String adGroupResourceName = ResourceNames.adGroup(Long.parseLong(customerId), Long.parseLong(adGroupId));
			AdGroupCriterion criterion;
			if(finalUrl.equals(""))
			{
				criterion = AdGroupCriterion.newBuilder()
								.setAdGroup(adGroupResourceName)
								.setStatus(AdGroupCriterionStatus.valueOf(status))
								.setKeyword(keywordInfo)
								.build();
			 
			}
			else
			{
			 criterion = AdGroupCriterion.newBuilder()
		 	 		.setAdGroup(adGroupResourceName)
		 	 		.setStatus(AdGroupCriterionStatus.valueOf(status))
		 	 		.setKeyword(keywordInfo)
		 	 		.addFinalUrls(finalUrl)
		 	 		.build();
			}
			AdGroupCriterionOperation op = AdGroupCriterionOperation.newBuilder().setCreate(criterion).build();
			operations.add(op);
		}
		AdGroupCriterionServiceClient agcServiceClient = googleAdsClient.getLatestVersion().createAdGroupCriterionServiceClient();
		
		List<PolicyViolationKey> exemptibleKeys = new ArrayList<>();
		
		MutateAdGroupCriteriaResponse response = null;
		
		boolean error = false;
		try
		{
			response = agcServiceClient.mutateAdGroupCriteria(customerId, operations);
			// If the request succeeded, then either the keyword does not require a policy exemption or
			// a policy exemption was previously submitted for the keyword. In either case, returns and
			// skips the remaining portion of this example that resubmits with an exemption request.
			System.out.printf(response.getResults(0).getResourceName());
		}
		catch(GoogleAdsException e)
		{
			exemptibleKeys = extractExemptiblePolicyViolationKeys(e);
			error = true;
		}
		if(error)
		{
			System.out.println("Attempting to add the keyword again by requesting exemption for its policy violations.");
			// Creates a modified version of the operation with the exempt policy violation keys.
		
			List<AdGroupCriterionOperation> operations1 = new ArrayList<>();
	
			for(int i = 0 ; i < operations.size() ; i++)
			{
				AdGroupCriterionOperation operation = AdGroupCriterionOperation.newBuilder().setCreate(operations.get(i).getCreate()).build();//
			
				operation = operations.get(i).toBuilder().addAllExemptPolicyViolationKeys(exemptibleKeys).build();
			
				operations1.add(operation);
			}
			
			// Tries sending the mutate request again.
			response = agcServiceClient.mutateAdGroupCriteria(customerId, operations1);
			System.out.printf("Successfully added a keyword with resource name '%s' by requesting policy violation exemptions.%n", response.getResults(0).getResourceName());
		}
		int index = 0;
		
		for(MutateAdGroupCriterionResult result : response.getResultsList())
		{
			output += keyword_ids[index];
			output += ";";
			output += result.getResourceName().split("~")[1];
			output += "~";
			index += 1;
		}
		try
		{
			agcServiceClient.shutdown();
			agcServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			agcServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		
		return output;
	}
	
	//	Collects all policy violation keys that can be exempted for sending an exemption request later
	private static List<PolicyViolationKey> extractExemptiblePolicyViolationKeys(GoogleAdsException googleAdsException)
	{
		List<PolicyViolationKey> exemptibleKeys = new ArrayList<>();
		System.out.println("Google Ads failure details:");
		for(GoogleAdsError googleAdsError : googleAdsException.getGoogleAdsFailure().getErrorsList())
		{
			System.out.printf("\t%s: %s%n", googleAdsError.getErrorCode(), googleAdsError.getMessage());
			if(googleAdsError.hasDetails() && googleAdsError.getDetails().hasPolicyViolationDetails())
			{
				PolicyViolationDetails policyViolationDetails =
						googleAdsError.getDetails().getPolicyViolationDetails();
				System.out.println("\tPolicy violation details:");
				System.out.printf("\t\tExternal policy name: '%s'%n", policyViolationDetails.getExternalPolicyName());
				System.out.printf("\t\tExternal policy description: '%s'%n", policyViolationDetails.getExternalPolicyDescription());
				System.out.printf("\t\tIs exemptible? '%s'%n", policyViolationDetails.getIsExemptible());
				if(policyViolationDetails.getIsExemptible() && policyViolationDetails.hasKey())
				{
					PolicyViolationKey policyViolationKey = policyViolationDetails.getKey();
					exemptibleKeys.add(policyViolationKey);
					System.out.println("\t\tPolicy violation key:");
					System.out.printf("\t\t\tName: '%s'%n", policyViolationKey.getPolicyName());
					System.out.printf("\t\t\tViolating text: '%s'%n", policyViolationKey.getViolatingText());
				}
			}
		}
		
		return exemptibleKeys;
	}

	public static String add_keyword_labels(GoogleAdsClient googleAdsClient, String customerId, String str) throws Throwable
	{	
		String[] Each_input;
		String[] Input_datas = str.split("~");
		
		List<AdGroupCriterionLabelOperation> operations = new ArrayList<>();
		String[] arr = new String[Input_datas.length];
		String data = "";
		
		for(int i = 0 ; i < Input_datas.length ; i++)
		{
			Each_input = Input_datas[i].split(";");
			
			String adGroupId = Each_input[0];
			String keywordId = Each_input[1];
			String LabelId = Each_input[2];
			arr[i] = Each_input[3];
			
			String AdGroupCriterionName = ResourceNames.adGroupCriterion(Long.parseLong(customerId), Long.parseLong(adGroupId), Long.parseLong(keywordId));
			String LabelName = ResourceNames.label(Long.parseLong(customerId), Long.parseLong(LabelId));
			
			AdGroupCriterionLabel adgrouplabel = AdGroupCriterionLabel.newBuilder()
													.setAdGroupCriterion(AdGroupCriterionName) //customers/{customer_id}/adGroupCriteria/{ad_group_id}~{criterion_id}
													.setLabel(LabelName)
													.build();
			
			AdGroupCriterionLabelOperation operation = AdGroupCriterionLabelOperation.newBuilder()
															.setCreate(adgrouplabel)
															.build();
			operations.add(operation);	
		}	
		
		MutateAdGroupCriterionLabelsRequest mutaterequest = MutateAdGroupCriterionLabelsRequest.newBuilder().setCustomerId(customerId).addAllOperations(operations).build();
		
		AdGroupCriterionLabelServiceClient adGroupAdServiceClient = googleAdsClient.getLatestVersion().createAdGroupCriterionLabelServiceClient();
		
		MutateAdGroupCriterionLabelsResponse mutatedlabel = adGroupAdServiceClient.mutateAdGroupCriterionLabels(mutaterequest);
		
		int i = 0;
		for(MutateAdGroupCriterionLabelResult result : mutatedlabel.getResultsList()) 
		{	
			data = data + arr[i] + ";";
			data = data + result.getResourceName().split("~")[2] + "~";
			i++;
		}

		data = data.substring(0, data.length() - 1);
		try
		{
			adGroupAdServiceClient.shutdown();
			adGroupAdServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			adGroupAdServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		
		return data;
	}
		
	public static String Negativekeywordfunction_Campaign(GoogleAdsClient googleAdsClient, String customerId, String Data) throws Throwable
	{
		String[] AdGroup_NegKeyword = Data.split("~");
		String output = "";
		
		String[] keyword_ids = new String[AdGroup_NegKeyword.length];
		List<CampaignCriterionOperation> operations = new ArrayList<>();
		
		for(int i = 0 ; i < AdGroup_NegKeyword.length ; i++)
		{
			String[] AdGroup_NegKeyword_ids = AdGroup_NegKeyword[i].split(";");
			keyword_ids[i] = AdGroup_NegKeyword_ids[3];
			String campId = AdGroup_NegKeyword_ids[0];
			String keywordText = AdGroup_NegKeyword_ids[1];
			String matchType = AdGroup_NegKeyword_ids[2];
			
			String adGroupResourceName = ResourceNames.campaign(Long.parseLong(customerId), Long.parseLong(campId));
			 
			KeywordInfo keywordInfo = KeywordInfo.newBuilder()
					 					.setText(keywordText)
					 					.setMatchType(KeywordMatchType.valueOf((matchType).toUpperCase()))
					 					.build();
			
			CampaignCriterion Adgroupnegcriter = CampaignCriterion.newBuilder()
													.setNegative(true)
													.setCampaign(adGroupResourceName)
													.setKeyword(keywordInfo)
													.build();
			
			CampaignCriterionOperation AdgroupOperation = CampaignCriterionOperation.newBuilder()
															.setCreate(Adgroupnegcriter)
															.build();
			operations.add(AdgroupOperation);
		}
		
		CampaignCriterionServiceClient agcServiceClient = googleAdsClient.getLatestVersion().createCampaignCriterionServiceClient();
		
		MutateCampaignCriteriaResponse response = agcServiceClient.mutateCampaignCriteria(customerId, operations);
		
		int index = 0;
		for(MutateCampaignCriterionResult result : response.getResultsList()) 
		{
			output += keyword_ids[index];
			output += ";";
			output += result.getResourceName().split("~")[1];
			output += "~";
			index += 1;
		}
		try
		{
			agcServiceClient.shutdown();
			agcServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			agcServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		output = output.substring(0, output.length() - 1);
		
		return output;		
	}
	
	public static String sync_adgroup_status_new(GoogleAdsClient googleAdsClient, String customerId, String AdgroupId) throws Throwable
	{ 
		int PAGE_SIZE = 10000;
		String[] Adgroupids = AdgroupId.split("~");
		String ReturnData = "";
		GoogleAdsServiceClient googleAdsServiceClient = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient();
		for(int a = 0 ; a < Adgroupids.length ; a++)
		{
			String AdGroupName = ResourceNames.adGroup(Long.parseLong(customerId), Long.parseLong( Adgroupids[a]));
	
			String searchQuery = "SELECT ad_group.id, "
									+ "ad_group.status "
									+ "FROM ad_group ";
			
			if(Adgroupids[a] != null)
			{
				searchQuery += String.format(" WHERE ad_group.resource_name = '%s' ", AdGroupName);
			}
			
			SearchGoogleAdsRequest request_S_snippets = SearchGoogleAdsRequest.newBuilder()
															.setCustomerId((customerId))
															.setPageSize(PAGE_SIZE)
															.setQuery(searchQuery)
															.build();
			 
			SearchPagedResponse searchPagedResponse_S_snippets = googleAdsServiceClient.search(request_S_snippets);
			 
			 
			for(GoogleAdsRow googleAdsRow : searchPagedResponse_S_snippets.iterateAll())
			{
				AdGroup CampOut = googleAdsRow.getAdGroup();
				ReturnData = ReturnData + Adgroupids[a] + ";";
				if(CampOut.getStatus() == null)
				{
					ReturnData = ReturnData + ""+ "~";
				}
				else
				{
					ReturnData = ReturnData + CampOut.getStatus()+ "~";
				}
			}
		}
		ReturnData = ReturnData.substring(0, ReturnData.length() - 1);
		
		return ReturnData;
	}
	
	public static String sync_keyword_status(GoogleAdsClient googleAdsClient, String customerId, String Data) throws Throwable
	{ 
		int PAGE_SIZE = 10000;
		String[] adgroup_keyword = Data.split("~");
		
		String ReturnData = "";
		GoogleAdsServiceClient googleAdsServiceClient = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient();
		for(int i = 0 ; i < adgroup_keyword.length ; i++)
		{
			String[] adgroup_keyword_ids = adgroup_keyword[i].split(";");
			String AdGroupId = adgroup_keyword_ids[0];
			String KeywordId = adgroup_keyword_ids[1];
		
			String searchQuery = "SELECT ad_group.id, "
									+ "ad_group_criterion.criterion_id, "
									+ "ad_group_criterion.status "
									+ "FROM ad_group_criterion "
									+ "WHERE ad_group_criterion.type = 'KEYWORD' "
									+ "AND ad_group_criterion.negative = FALSE "
									+ " AND ad_group.id = " + AdGroupId
									+ " AND ad_group_criterion.criterion_id IN (" + KeywordId + ")";
								
			String next_page = "";
			
			do
			{
				SearchGoogleAdsRequest request_S_snippets = SearchGoogleAdsRequest.newBuilder()
																.setCustomerId((customerId))
																.setPageSize(PAGE_SIZE)
																.setPageToken(next_page)
																.setQuery(searchQuery)
																.build();
			
				SearchPagedResponse searchPagedResponse_S_snippets = googleAdsServiceClient.search(request_S_snippets);
		
				next_page = searchPagedResponse_S_snippets.getNextPageToken();
			 
				SearchPage searchPage = searchPagedResponse_S_snippets.getPage();
				
				for(GoogleAdsRow googleAdsRow : searchPage.getValues())
				{
					AdGroup CampOut = googleAdsRow.getAdGroup();
					AdGroupCriterion Keyinfo = googleAdsRow.getAdGroupCriterion();
					ReturnData = ReturnData + adgroup_keyword_ids[0] + ";"; //AdGroup id 
					ReturnData = ReturnData + Keyinfo.getCriterionId() + ";"; //Adw_keyword id
					if(CampOut.getStatus() == null) {
						ReturnData = ReturnData + ""+ "~";
					}
					else {
						ReturnData = ReturnData + Keyinfo.getStatus() + "~"; //Keyword Status
					}
				}
			}
			while(!next_page.equals(""));	 
		}
		ReturnData = ReturnData.substring(0, ReturnData.length() - 1);
		
		return ReturnData;
	}
	
	public static String sync_keyword_label(GoogleAdsClient googleAdsClient, String customerId, String Data) throws Throwable
	{	
		int PAGE_SIZE = 1000;
		String[] adgroup_keywords = Data.split("~");
		String ReturnData = "";
		GoogleAdsServiceClient googleAdsServiceClient = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient();
		for(int i = 0 ; i < adgroup_keywords.length ; i++)
		{
			String[] adgroup_keywords_ids = adgroup_keywords[i].split(";");
			String AdGroupId = adgroup_keywords_ids[0];
			String KeywordId = adgroup_keywords_ids[1];
		
			String searchQuery = "SELECT ad_group.id, "
									+ "ad_group_criterion.criterion_id, "
									+ "ad_group_criterion.status, "
									+ "ad_group_criterion.labels "
									+ "FROM ad_group_criterion "
									+ "WHERE ad_group_criterion.type = 'KEYWORD' "
									+ "AND ad_group_criterion.negative = FALSE "
									+ " AND ad_group.id = " + AdGroupId
									+ " AND ad_group_criterion.criterion_id IN (" + KeywordId + ")";
								
			String next_page = "";
			
			do
			{
				SearchGoogleAdsRequest request_S_snippets = SearchGoogleAdsRequest.newBuilder()
																.setCustomerId((customerId))
																.setPageSize(PAGE_SIZE)
																.setPageToken(next_page)
																.setQuery(searchQuery)
																.build();
			
				SearchPagedResponse searchPagedResponse_S_snippets = googleAdsServiceClient.search(request_S_snippets);
				next_page = searchPagedResponse_S_snippets.getNextPageToken();
			 
				SearchPage searchPage = searchPagedResponse_S_snippets.getPage();
				
				for(GoogleAdsRow googleAdsRow : searchPage.getValues())
				{
					AdGroupCriterion Keyinfo = googleAdsRow.getAdGroupCriterion();
					ReturnData = ReturnData + adgroup_keywords_ids[0] + ";"; //AdGroup id 
					ReturnData = ReturnData + Keyinfo.getCriterionId() + ";"; //Adw_keyword id
					for(int index = 0 ; index < Keyinfo.getLabelsCount() ; index++)
					{
						ReturnData = ReturnData + Keyinfo.getLabels(index).split("/")[3] + "*";
					}
					ReturnData = ReturnData.substring(0, ReturnData.length() - 1);
					ReturnData = ReturnData + "~";
				}
		 	}
			while(!next_page.equals(""));
		}
		try
		{
			googleAdsServiceClient.shutdown();
			googleAdsServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			googleAdsServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		ReturnData = ReturnData.substring(0, ReturnData.length() - 1);
		
		return ReturnData;
	}
	
	public static String getaccount_labels(GoogleAdsClient googleAdsClient, String customerId) throws Throwable
	{ 	
		String ReturnData = "";
		GoogleAdsServiceClient googleAdsServiceClient = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient();
		
		String searchQuery = "SELECT label.resource_name, label.id, label.name FROM label WHERE customer.resource_name ='" + "customers/" + customerId + "'";
		
		String next_page = "";
		do
		{
			SearchGoogleAdsRequest request_S_snippets = SearchGoogleAdsRequest.newBuilder()
															.setCustomerId((customerId))
															.setPageSize(PAGE_SIZE)
															.setPageToken(next_page)
															.setQuery(searchQuery)
															.build();
		
			SearchPagedResponse searchPagedResponse_S_snippets = googleAdsServiceClient.search(request_S_snippets);
			next_page = searchPagedResponse_S_snippets.getNextPageToken();
		 
			SearchPage searchPage = searchPagedResponse_S_snippets.getPage();
			
			for(GoogleAdsRow googleAdsRow : searchPage.getValues())
			{
				Label Lbl = googleAdsRow.getLabel();
				ReturnData = ReturnData + Lbl.getId();
				ReturnData = ReturnData + "~";
			}
	 	}
		while(!next_page.equals(""));
		
		ReturnData = ReturnData.substring(0, ReturnData.length() - 1);
		
		return ReturnData;
	}
	
	public static void delete_labels(GoogleAdsClient googleAdsClient, String customerId, String str) throws Throwable
	{	
		String[] Input_datas = str.split("~");
	
		List<LabelOperation> operations = new ArrayList<>();
		
		for(int i = 0 ; i < Input_datas.length ; i++)
		{	
			String LabelId = Input_datas[i];
			String LabelName = ResourceNames.label(Long.parseLong(customerId), Long.parseLong(LabelId));
		
			LabelOperation operation = LabelOperation.newBuilder().setRemove(LabelName).build();
			operations.add(operation);	
		}		
		MutateLabelsRequest mutaterequest = MutateLabelsRequest.newBuilder().setCustomerId(customerId).addAllOperations(operations).build();
		
		LabelServiceClient adGroupAdServiceClient = googleAdsClient.getLatestVersion().createLabelServiceClient();
		adGroupAdServiceClient.mutateLabels(mutaterequest);
	
		return;
	}
	
	private static AdTextAsset createAdTextAsset(String text) throws Throwable
	{
		return AdTextAsset.newBuilder().setText(text).build();
	}
	
	public static String addResponsiveSearchAds(GoogleAdsClient googleAdsClient, String customerId, String data) throws Throwable
	{
		List<String> list_of_ads = new ArrayList<>();
		list_of_ads.addAll(Arrays.asList(data.split("~")));
		String output = "";
		long[] rsaIds = new long[list_of_ads.size()];
	
		// A list to store Operations
		List<AdGroupAdOperation> operations = new ArrayList<>();
		
		// A list to store headlines
		List<AdTextAsset> headlines = new ArrayList<>();
	
		// Iterate through the list of ETAds created above and split data further on ;
		for(int i = 0 ; i < list_of_ads.size() ; i++)
		{
			String[] values = list_of_ads.get(i).split(";");
			long adGroupId = Long.parseLong(values[0]);
			
			// Use the @createAdTextAsset function to create an AdTextAsset and add it in the headlines list
			for(int h = 1 ; h <= 15 ; h++)
			{
				if(!values[h].equals(""))
					headlines.add(createAdTextAsset(values[h]));
			}

			String description1 = values[16];
			String description2 = values[17];
			String description3 = values[18];
			String description4 = values[19];
			String path1 = values[20];
			String path2 = values[21];
			
			String finalUrl = values[22];
			String status = values[23];
			String rsa_id = values[24];
			
			rsaIds[i] = Long.parseLong(rsa_id);

			String adGroupResourceName = ResourceNames.adGroup(Long.parseLong(customerId), adGroupId);
			ResponsiveSearchAdInfo responsiveSearchAdInfo = null;
			if(!description3.equals(""))
			{
				responsiveSearchAdInfo = ResponsiveSearchAdInfo.newBuilder()
											.addAllHeadlines(headlines)
											.addDescriptions(createAdTextAsset(description1))
											.addDescriptions(createAdTextAsset(description2))
											.addDescriptions(createAdTextAsset(description3))
											.addDescriptions(createAdTextAsset(description4))
											.setPath1(path1)
											.setPath2(path2)
											.build();
			}
			else
			{
				responsiveSearchAdInfo = ResponsiveSearchAdInfo.newBuilder()
											.addAllHeadlines(headlines)
											.addDescriptions(createAdTextAsset(description1))
											.addDescriptions(createAdTextAsset(description2))
											.setPath1(path1)
											.setPath2(path2)
											.build();
			}
				
			headlines.clear();
			// Creates an Ad
			Ad ad = Ad.newBuilder()
					.setResponsiveSearchAd(responsiveSearchAdInfo)
					.addFinalUrls(finalUrl)
					.build();
			
			// Set ad group in using the adGroupId
			AdGroupAd adGroupAd = AdGroupAd.newBuilder()
									.setAdGroup(adGroupResourceName)
									.setStatus(AdGroupAdStatus.valueOf(status))
									.setAd(ad)
									.build();
			
			// Create an operation and add it in the operation list
			AdGroupAdOperation operation = AdGroupAdOperation.newBuilder().setCreate(adGroupAd).build();
			operations.add(operation);
		}
		
		AdGroupAdServiceClient adGroupAdServiceClient = googleAdsClient.getLatestVersion().createAdGroupAdServiceClient();
		MutateAdGroupAdsResponse response = adGroupAdServiceClient.mutateAdGroupAds(customerId, operations);
		int index = 0;
		
		// Generates the output string using the value of result
		for(MutateAdGroupAdResult result : response.getResultsList())
		{
			output += rsaIds[index];
			output += ";";
			output += result.getResourceName().split("~")[1];
			output += "~";
			index += 1;
		}
		
		// Removes the ~ on the last index
		output = output.substring(0, output.length() - 1);
		
		return output;
	}
	
	public static void sync_eta (GoogleAdsClient googleAdsClient, Long[] client , String filepath , String Duration) throws Throwable
	{	
		String query = "SELECT customer.descriptive_name, campaign.id, ad_group.id, ad_group.name, campaign.name, "
						+ "ad_group_ad.ad.id, ad_group_ad.ad.expanded_text_ad.headline_part1, ad_group_ad.ad.expanded_text_ad.headline_part2,"
						+ " ad_group_ad.ad.expanded_text_ad.headline_part3, ad_group_ad.ad.expanded_text_ad.description, "
						+ "ad_group_ad.ad.expanded_text_ad.description2, ad_group_ad.ad.final_urls, "
						+ "ad_group_ad.ad.expanded_text_ad.path1, ad_group_ad.ad.expanded_text_ad.path2, "
						+ "ad_group_ad.status, ad_group_ad.ad.type FROM ad_group_ad WHERE"
						+ " ad_group_ad.ad.type = EXPANDED_TEXT_AD";
		List<String> GAQL_QUERY_STRINGS = ImmutableList.of(query);
		GoogleAdsServiceClient serviceClient = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient();
		
		for(String gaqlQuery : GAQL_QUERY_STRINGS)
		{
			for(Long customerId2 : client)
			{
				File file1 = new File(filepath);
				file1.mkdir();
				String str1 = customerId2.toString();
				File myObj = new File(filepath + str1 + "_ETA.csv");
				if(myObj.createNewFile())
				{
					myObj.setWritable(true);
					myObj.setReadable(true);
				}
				String final_urls = "";
				BufferedWriter myWriter = new BufferedWriter(new FileWriter(filepath + str1 + "_ETA.csv", false));
				SearchGoogleAdsStreamRequest request = SearchGoogleAdsStreamRequest.newBuilder().setCustomerId(customerId2.toString()).setQuery(gaqlQuery).build();
				
				ServerStream<SearchGoogleAdsStreamResponse> stream = serviceClient.searchStreamCallable().call(request);
				myWriter.write("CustomerDescriptiveName,CampaignId,CampaignName,AdGroupId,AdGroupName,Id,HeadlinePart1,HeadlinePart2,ExpandedTextAdHeadlinePart3,Description,ExpandedTextAdDescription2,CreativeFinalUrls,Path1,Path2,Status \n");
				
				for(SearchGoogleAdsStreamResponse response : stream)
				{
					for(GoogleAdsRow googleAdsRow : response.getResultsList())
					{
						myWriter.write('"' + googleAdsRow.getCustomer().getDescriptiveName()+'"' + ";");
						myWriter.write(googleAdsRow.getCampaign().getId() + ";");
						myWriter.write('"' + googleAdsRow.getCampaign().getName()+'"' + ";");
						myWriter.write(googleAdsRow.getAdGroup().getId() + ";");
						myWriter.write('"' + googleAdsRow.getAdGroup().getName()+'"' + ";");
						myWriter.write(googleAdsRow.getAdGroupAd().getAd().getId() + ";");
						myWriter.write('"' + googleAdsRow.getAdGroupAd().getAd().getExpandedTextAd().getHeadlinePart1()+'"' + ";");
						myWriter.write('"' + googleAdsRow.getAdGroupAd().getAd().getExpandedTextAd().getHeadlinePart2()+'"' + ";");
						myWriter.write('"' + googleAdsRow.getAdGroupAd().getAd().getExpandedTextAd().getHeadlinePart3()+'"' + ";");
						myWriter.write(googleAdsRow.getAdGroupAd().getAd().getExpandedTextAd().getDescription() + ";");
						myWriter.write(googleAdsRow.getAdGroupAd().getAd().getExpandedTextAd().getDescription2() + ";");
						String url = googleAdsRow.getAdGroupAd().getAd().getFinalUrlsList().toString();
						final_urls = url.replace("[","").replace("]", "");
						myWriter.write(final_urls + ";");
						myWriter.write(googleAdsRow.getAdGroupAd().getAd().getExpandedTextAd().getPath1() + ";");
						myWriter.write(googleAdsRow.getAdGroupAd().getAd().getExpandedTextAd().getPath2() + ";");
						myWriter.write(googleAdsRow.getAdGroupAd().getStatus() + ";");
						myWriter.write("\n");
					}
				}
				myWriter.close();
			}
		}	
	}
	
	public static void sync_rsa (GoogleAdsClient googleAdsClient, Long[] client , String filepath , String Duration) throws Throwable
	{	
		String query = "SELECT customer.descriptive_name, ad_group.id, campaign.id, ad_group.name, campaign.name, "
						+ "ad_group_ad.ad.id, ad_group_ad.ad.responsive_search_ad.headlines, "
						+ "ad_group_ad.ad.responsive_search_ad.descriptions, ad_group_ad.ad.responsive_search_ad.path1, "
						+ "ad_group_ad.ad.responsive_search_ad.path2, ad_group_ad.ad.final_urls, ad_group_ad.status, ad_group_ad.ad.type FROM ad_group_ad "
						+ "WHERE ad_group_ad.ad.type = RESPONSIVE_SEARCH_AD";
		List<String> GAQL_QUERY_STRINGS = ImmutableList.of(query);
		GoogleAdsServiceClient serviceClient = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient();
		for(String gaqlQuery : GAQL_QUERY_STRINGS)
		{
			for(Long customerId2 : client)
			{
				File file1 = new File(filepath);
				file1.mkdir();
				
				String str1 = customerId2.toString();
				File myObj = new File(filepath + str1 + "_RSA.csv");
				if(myObj.createNewFile())
				{
					myObj.setWritable(true);
					myObj.setReadable(true);
				}
				String final_urls = "";
				
				BufferedWriter myWriter = new BufferedWriter(new FileWriter(filepath + str1 + "_RSA.csv", false));
				SearchGoogleAdsStreamRequest request = SearchGoogleAdsStreamRequest.newBuilder().setCustomerId(customerId2.toString()).setQuery(gaqlQuery).build();
				
				ServerStream<SearchGoogleAdsStreamResponse> stream = serviceClient.searchStreamCallable().call(request);
				myWriter.write("CustomerDescriptiveName;CampaignId;CampaignName;AdGroupId;AdGroupName;Id;ResponsiveSearchAdHeadlines;ResponsiveSearchAdDescriptions;CreativeFinalUrls;ResponsiveSearchAdPath1;ResponsiveSearchAdPath2;Status \n");
				for(SearchGoogleAdsStreamResponse response : stream)
				{
					for(GoogleAdsRow googleAdsRow : response.getResultsList())
					{
						StringBuilder str = new StringBuilder();
						StringBuilder desc = new StringBuilder();
						myWriter.write('"' + googleAdsRow.getCustomer().getDescriptiveName() + '"' + ";");
						myWriter.write( googleAdsRow.getCampaign().getId() + ";");
						myWriter.write('"' + googleAdsRow.getCampaign().getName() + '"' + ";");
						myWriter.write(googleAdsRow.getAdGroup().getId() + ";");
						myWriter.write('"' + googleAdsRow.getAdGroup().getName() + '"' + ";");
						myWriter.write(googleAdsRow.getAdGroupAd().getAd().getId() + ";");
						
						@SuppressWarnings("rawtypes")
						Iterator s = googleAdsRow.getAdGroupAd().getAd().getResponsiveSearchAd().getHeadlinesList().listIterator();
						while(s.hasNext())
						{
							String si = s.next().toString();
							int sf = si.indexOf(":");
							int sb = si.indexOf("asset_performance_label");
							sf = sf + 1;
							while(sf<sb)
							{
								str.append(si.charAt(sf));
								sf++;							 
							}
						}
						
						myWriter.write(str.toString().replace("\n", " ").replace(":", " ").replace(",", " ")+ ";"); 
						
						@SuppressWarnings("rawtypes")
						Iterator des = googleAdsRow.getAdGroupAd().getAd().getResponsiveSearchAd().getDescriptionsList().listIterator();
						while(des.hasNext())
						{
							String si = des.next().toString();
							int sf = si.indexOf(":");
							int sb = si.indexOf("asset_performance_label");
							sf = sf + 1;
							while(sf<sb)
							{
								desc.append(si.charAt(sf));
								sf++;
							}
						}
						myWriter.write(desc.toString().replace("\n", " ").replace(":", " ").replace(",", " ")+ ";");
						String url = googleAdsRow.getAdGroupAd().getAd().getFinalUrlsList().toString();
						final_urls = url.replace("[","").replace("]", "");
						myWriter.write(final_urls + ";");
						myWriter.write(googleAdsRow.getAdGroupAd().getAd().getResponsiveSearchAd().getPath1() + ";");
						myWriter.write(googleAdsRow.getAdGroupAd().getAd().getResponsiveSearchAd().getPath2() + ";");
						myWriter.write(googleAdsRow.getAdGroupAd().getStatus() + ";");
						myWriter.write("\n");
					}
				}
				myWriter.close();
			} 
		}	
	}
	
	//---DSA---
	public static String addAdGroup_dynamic_test(GoogleAdsClient googleAdsClient, String customerId, String data)
	{
		List<String> list_of_groups = new ArrayList<>();
		list_of_groups.addAll(Arrays.asList(data.split("~")));
		
		String output = "";
		long[] table_ids = new long[list_of_groups.size()];
		List<AdGroupOperation> operations = new ArrayList<>();
		
		for(int i = 0 ; i < list_of_groups.size() ; i++)
		{
			String[] values = list_of_groups.get(i).split(";");
			String campaignResourceName = ResourceNames.campaign( Long.parseLong(customerId), Long.parseLong(values[0]));
			String adw_adgroup_name = values[1];
			String status = values[2];
			float max_cpc1 = Float.parseFloat(values[3]);
			
			long max_cpc =(long) (Math.round(max_cpc1*1000)* 1000);
			//String rotation = values[4];
			String table_id = values[5];
			
			
			TargetRestriction target_obs = TargetRestriction.newBuilder()
											.setTargetingDimension(TargetingDimension.AUDIENCE)
											.setBidOnly(true)
											.build();
			
			table_ids[i] = Long.parseLong(table_id);
			AdGroup adGroup = AdGroup.newBuilder()
	 								 .setName(adw_adgroup_name)
	 								 .setStatus(AdGroupStatus.valueOf(status))
									 .setCampaign(campaignResourceName)
									 .setCpcBidMicros(max_cpc)
									 .setTargetingSetting(TargetingSetting.newBuilder()
									 .addTargetRestrictions(target_obs).build())
									 .setType(AdGroupType.SEARCH_DYNAMIC_ADS)
									 .build();
			AdGroupOperation Camp_operation = AdGroupOperation.newBuilder().setCreate(adGroup).build();
			operations.add(Camp_operation);
		}
		
		AdGroupServiceClient adGroupServiceClient = googleAdsClient.getLatestVersion().createAdGroupServiceClient(); 
		MutateAdGroupsResponse adGroup_response = adGroupServiceClient.mutateAdGroups(String.valueOf(customerId), operations);
		
		int index = 0;
		for(MutateAdGroupResult result : adGroup_response.getResultsList())
		{
			output += table_ids[index];
			output += ";";
			output += result.getResourceName().split("/")[3];
			output += "~";
			index += 1;
		}
		try
		{
			adGroupServiceClient.shutdown();
			adGroupServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			adGroupServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		output = output.substring(0, output.length() - 1);
		
		return output;
	}

	public static String addExpandedDSA_dynamic(GoogleAdsClient googleAdsClient, String customerId, String data)
	{
		List<String> list_of_groups = new ArrayList<>();
		list_of_groups.addAll(Arrays.asList(data.split("~")));
		
		String output = "";
		long[] table_ids = new long[list_of_groups.size()];
		
		List<AdGroupAdOperation> operations = new ArrayList<>();
					
		for(int i = 0 ; i < list_of_groups.size() ; i++)
		{
			String[] values = list_of_groups.get(i).split(";");
			String adGroupResourceName = ResourceNames.adGroup( Long.parseLong(customerId), Long.parseLong(values[0]));
			
			String status = values[1];
			String description1 = values[2];
			String description2 = values[3];
			
			String table_id = values[4];
			table_ids[i] = Long.parseLong(table_id);

			AdGroupAd adGroupAd = AdGroupAd.newBuilder()
									.setAdGroup(adGroupResourceName)
									.setStatus(AdGroupAdStatus.valueOf(status))
									.setAd(Ad.newBuilder()
											.setExpandedDynamicSearchAd(ExpandedDynamicSearchAdInfo.newBuilder()
																			.setDescription(description1)
																			.setDescription2(description2)
																			.build())
											.build())
									.build();

			// Creates the operation.
			AdGroupAdOperation operation = AdGroupAdOperation.newBuilder().setCreate(adGroupAd).build();
			operations.add(operation);
		}

		AdGroupAdServiceClient adGroupAdServiceClient = googleAdsClient.getLatestVersion().createAdGroupAdServiceClient();
			
		MutateAdGroupAdsResponse response = adGroupAdServiceClient.mutateAdGroupAds(String.valueOf(customerId), operations);
			
		int index = 0;
		for(MutateAdGroupAdResult result : response.getResultsList())
		{
			output += table_ids[index];
			output += ";";
			output += result.getResourceName().split("~")[1];
			output += "~";
			index += 1;
		}
		try
		{
			adGroupAdServiceClient.shutdown();
			adGroupAdServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			adGroupAdServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		output = output.substring(0, output.length() - 1);
		
		return output;
	}
	
	// Dynamic Ad Target
	public static String addWebPageCriteria_dynamic(GoogleAdsClient googleAdsClient, String customerId, String data)
	{
					
		List<String> list_of_groups = new ArrayList<>();
		list_of_groups.addAll(Arrays.asList(data.split("~")));
		
		String output = "";
		long[] table_ids = new long[list_of_groups.size()];
		
		List<AdGroupCriterionOperation> operations = new ArrayList<>();
				
		for(int i = 0 ; i < list_of_groups.size() ; i++)
		{
			String[] values = list_of_groups.get(i).split(";");
			String adGroupResourceName = ResourceNames.adGroup( Long.parseLong(customerId), Long.parseLong(values[0]));
			String url = values[2];
			String table_id = values[1];
			table_ids[i] = Long.parseLong(table_id);
			
			WebpageConditionInfo webpageConditionInfo = WebpageConditionInfo.newBuilder()
															.setOperand(WebpageConditionOperand.URL)
															.setArgument(url)
															.build();

			// Creates the webpage info, or criterion for targeting webpages of an advertiser's website.
			WebpageInfo webpageInfo = WebpageInfo.newBuilder()
										.setCriterionName("Test Criterion 9")
										.addAllConditions(ImmutableList.of(webpageConditionInfo))
										.build();

			// Creates the ad group criterion.
			AdGroupCriterion adGroupCriterion =	AdGroupCriterion.newBuilder()
													.setAdGroup(adGroupResourceName)
													.setWebpage(webpageInfo)
													.setCpcBidMicros(1_500_000)
													.build();

			// Creates the operation.
			AdGroupCriterionOperation operation = AdGroupCriterionOperation.newBuilder()
													.setCreate(adGroupCriterion)
													.build();
			operations.add(operation);
		}
		AdGroupCriterionServiceClient agcServiceClient = googleAdsClient.getLatestVersion().createAdGroupCriterionServiceClient();
					
		MutateAdGroupCriteriaResponse response = agcServiceClient.mutateAdGroupCriteria(customerId, operations);
		int index = 0;
		for(MutateAdGroupCriterionResult result : response.getResultsList())
		{
			output += table_ids[index];
			output += ";";
			output += result.getResourceName().split("~")[1];
			output += "~";
			index += 1;
		}
		try
		{
			agcServiceClient.shutdown();
			agcServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			agcServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		output = output.substring(0, output.length() - 1);
		
		return output;
	}
	
	// Page Feed
	public String createAssetSet_dynamic(GoogleAdsClient googleAdsClient, long customerId, String data)
	{
		List<String> list_of_groups = new ArrayList<>();
		list_of_groups.addAll(Arrays.asList(data.split("~")));
		
		String output = "";
		long[] table_ids = new long[list_of_groups.size()];
		
		List<AssetSetOperation> Operations = new ArrayList<>();

		for(int i = 0 ; i < list_of_groups.size() ; i++)
		{
			String[] values = list_of_groups.get(i).split(";");
			String table_id = values[0];			
			String pagefeedname = values[1];
			
			table_ids[i] = Long.parseLong(table_id);
		
			AssetSet assetSet = AssetSet.newBuilder()
									.setName(pagefeedname)
									.setType(AssetSetType.PAGE_FEED)
									.build();

			// Creates an operation to add the AssetSet.
			AssetSetOperation operation = AssetSetOperation.newBuilder()
											.setCreate(assetSet)
											.build();
			Operations.add(operation);
		}
		AssetSetServiceClient serviceClient = googleAdsClient.getLatestVersion().createAssetSetServiceClient() ;
		
		// Sends the mutate request.
		MutateAssetSetsResponse response = serviceClient.mutateAssetSets(String.valueOf(customerId), Operations);
				
		int index = 0;
		for(MutateAssetSetResult result : response.getResultsList())
		{
			output += table_ids[index];
			output += ";";
			output += result.getResourceName().split("/")[3];
			output += "~";
			index += 1;
		}
		try
		{
			serviceClient.shutdown();
			serviceClient.awaitTermination(5, TimeUnit.SECONDS);
			serviceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		
		return output;
	}

	//Create URLs
	public static String createAssets_dynamic(GoogleAdsClient googleAdsClient, String customerId, String data)
	{
		List<String> list_of_groups = new ArrayList<>();
		list_of_groups.addAll(Arrays.asList(data.split("~")));
		
		String output = "";
		long[] table_ids = new long[list_of_groups.size()];
		
		List<AssetOperation> assetOperations = new ArrayList<>();
		
		for(int i = 0 ; i < list_of_groups.size() ; i++)
		{
			String[] values = list_of_groups.get(i).split(";");
						
			String label = values[0];
			String table_id = values[1];
			String url = values[2];
			table_ids[i] = Long.parseLong(table_id);
					
			PageFeedAsset pageFeedAsset = PageFeedAsset.newBuilder()
											// Sets the URL of the page to include.
											.setPageUrl(url)
											// Recommended: adds labels to the asset. These labels can be used later in ad group targeting to restrict the set of pages that can serve.
											.addLabels(label)
											.build();
			
			Asset asset = Asset.newBuilder()
							.setPageFeedAsset(pageFeedAsset)
							.build();
			assetOperations.add(AssetOperation.newBuilder()
									.setCreate(asset)
									.build());
		}
	
		// Creates the service client.
		AssetServiceClient assetServiceClient = googleAdsClient.getLatestVersion().createAssetServiceClient();
		
		// Adds the assets.
		MutateAssetsResponse response = assetServiceClient.mutateAssets(String.valueOf(customerId), assetOperations);
		
		int index = 0;
		for(MutateAssetResult result : response.getResultsList())
		{
			output += table_ids[index];
			output += ";";
			output += result.getResourceName().split("/")[3];
			output += "~";
			index += 1;
		}
		try
		{
			assetServiceClient.shutdown();
			assetServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			assetServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		
		return output;
	}

	// Adds an Asset to an AssetSet by creating an AssetSetAsset link.
	public String addAssetsToAssetSet_dynamic(GoogleAdsClient googleAdsClient, String assetSetResourceName, long customerId, String data)
	{		
		List<String> list_of_groups = new ArrayList<>();
		list_of_groups.addAll(Arrays.asList(data.split("~")));
		
		String output = "";
		long[] table_ids = new long[list_of_groups.size()];
		
		// [START add_asset_set_asset]
		List<AssetSetAssetOperation> operations = new ArrayList<>();
		
		for(int i = 0 ; i < list_of_groups.size() ; i++)
		{
			String[] values = list_of_groups.get(i).split(";");
			String assetResourcename = values[0];
			String table_id = values[1];
			table_ids[i] = Long.parseLong(table_id);
					
			AssetSetAsset assetSetAsset = AssetSetAsset.newBuilder()
											.setAsset(assetResourcename)
											//.setStatus(AssetSetAssetStatus.REMOVED)
											.setAssetSet(assetSetResourceName)
											.build();
			// Creates an operation to add the link.
			AssetSetAssetOperation operation = AssetSetAssetOperation.newBuilder()
													.setCreate(assetSetAsset)
													.build();
			operations.add(operation);
		}
		
		AssetSetAssetServiceClient client = googleAdsClient.getLatestVersion().createAssetSetAssetServiceClient(); 

		// Sends the mutate request.
		client.mutateAssetSetAssets(String.valueOf(customerId), operations);
		 
		int index = 0;
		for(@SuppressWarnings("unused") long table_id : table_ids)
		{
			output += table_ids[index];
			output += ";";
			output += index;
			output += '~';
			index += 1;
		}
		
		output = output.substring(0, output.length() - 1);
		
		return output;
	}


	// Links an AssetSet to a Campaign by creating a CampaignAssetSet.
	public void linkAssetSetToCampaign(GoogleAdsClient googleAdsClient,	String assetSetResourceName, long customerId, long campaignId)
	{
		// [START add_campaign_asset_set]
		// Creates a CampaignAssetSet representing the link between an AssetSet and a Campaign.
		CampaignAssetSet campaignAssetSet = CampaignAssetSet.newBuilder()
												.setCampaign(ResourceNames.campaign(customerId, campaignId))
												.setAssetSet(assetSetResourceName)
												.build();
		// Creates an operation to add the CampaignAssetSet.
		CampaignAssetSetOperation operation = CampaignAssetSetOperation.newBuilder()
												.setCreate(campaignAssetSet)
												.build();
		// Creates the service client.
		try (CampaignAssetSetServiceClient client = googleAdsClient.getLatestVersion().createCampaignAssetSetServiceClient())
		{
			// Issues the mutate request.
			MutateCampaignAssetSetsResponse response =
			client.mutateCampaignAssetSets(
			String.valueOf(customerId), ImmutableList.of(operation));
			String resourceName = response.getResults(0).getResourceName();
			System.out.printf("Created a CampaignAssetSet with resource name %s.%n", resourceName);
		}
	}
	
	// Update campaign to dynamic
	public static void updateCampaign(GoogleAdsClient googleAdsClient, Long customerId, Long campaignId, String clientWebsite)
	{
		try (CampaignServiceClient campaignServiceClient = googleAdsClient.getLatestVersion().createCampaignServiceClient())
		{
			// Creates a Campaign object with the proper resource name and any other changes.
			Campaign campaign = Campaign.newBuilder()
									.setResourceName(ResourceNames.campaign(customerId, campaignId))
									.setStatus(CampaignStatus.PAUSED)
									.setDynamicSearchAdsSetting(DynamicSearchAdsSetting.newBuilder()
																	.setDomainName(clientWebsite)
																	.setLanguageCode("en")
																	.setUseSuppliedUrlsOnly(true)
																	.build())
									.build();
			// Constructs an operation that will update the campaign, using the FieldMasks utility to
			// derive the update mask. This mask tells the Google Ads API which attributes of the
			// campaign you want to change.
			CampaignOperation operation = CampaignOperation.newBuilder()
											.setUpdate(campaign)
											.setUpdateMask(FieldMasks.allSetFieldsOf(campaign))
											.build();
			
			// Sends the operation in a mutate request.
			MutateCampaignsResponse response = campaignServiceClient.mutateCampaigns(customerId.toString(), Collections.singletonList(operation));
			
			// Prints the resource name of each updated object.
			for(MutateCampaignResult mutateCampaignResult : response.getResultsList())
			{
				System.out.printf("Updated campaign with resourceName: %s.%n", mutateCampaignResult.getResourceName());
			}
		}
	}

	public static String removeCriteria_dynamic(GoogleAdsClient googleAdsClient, String customerId, String data)
	{
		List<String> list_of_groups = new ArrayList<>();
		list_of_groups.addAll(Arrays.asList(data.split("~")));
		String output = "";
		long[] table_ids = new long[list_of_groups.size()];
		List<AdGroupCriterionOperation> operations = new ArrayList<>();
						
		for(int i = 0 ; i < list_of_groups.size() ; i++)
		{
			String[] values = list_of_groups.get(i).split(";");
			String adGroupCriterionResourceName = ResourceNames.adGroupCriterion( Long.parseLong(customerId), Long.parseLong(values[0]), Long.parseLong(values[1]));
				
			String table_id = values[2];
					
			table_ids[i] = Long.parseLong(table_id);
			// Creates the operation.
			AdGroupCriterionOperation operation = AdGroupCriterionOperation.newBuilder()
													.setRemove(adGroupCriterionResourceName).build();
			operations.add(operation);
		}
		AdGroupCriterionServiceClient agcServiceClient = googleAdsClient.getLatestVersion().createAdGroupCriterionServiceClient();
				
		MutateAdGroupCriteriaResponse response = agcServiceClient.mutateAdGroupCriteria(customerId, operations);
		int index = 0;
		for(MutateAdGroupCriterionResult result : response.getResultsList())
		{
			output += table_ids[index];
			output += ";";
			output += result.getResourceName().split("~")[1];
			output += "~";
			index += 1;
		}
		try
		{
			agcServiceClient.shutdown();
			agcServiceClient.awaitTermination(5, TimeUnit.SECONDS);
			agcServiceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		output = output.substring(0, output.length() - 1);
		
		return output;	
	}
	
	public static String sync_DAT(GoogleAdsClient googleAdsClient, String clientCustomerId, String adGroupId) throws Throwable
	{ 		
		int PAGE_SIZE = 100;
		
		String ReturnData = "";
		GoogleAdsServiceClient googleAdsServiceClient = googleAdsClient.getLatestVersion().createGoogleAdsServiceClient();
		 
		String adGroupResourceName = ResourceNames.adGroup(Long.parseLong(clientCustomerId), Long.parseLong(adGroupId));
		
		String searchQuery = "SELECT ad_group_criterion.ad_group, ad_group_criterion.webpage.conditions, ad_group_criterion.criterion_id FROM ad_group_criterion WHERE ad_group_criterion.type = 'WEBPAGE' AND ad_group_criterion.ad_group = '" + adGroupResourceName + "'";

		String next_page = "";
		do
		{
			SearchGoogleAdsRequest request_S_snippets = SearchGoogleAdsRequest.newBuilder()
															.setCustomerId(clientCustomerId)
															.setPageSize(PAGE_SIZE)
															.setPageToken(next_page)
															.setQuery(searchQuery)
															.build();

			SearchPagedResponse searchPagedResponse_S_snippets = googleAdsServiceClient.search(request_S_snippets);

			next_page = searchPagedResponse_S_snippets.getNextPageToken();
			
			SearchPage searchPage = searchPagedResponse_S_snippets.getPage();
			
			for(GoogleAdsRow googleAdsRow : searchPage.getValues())
			{
				AdGroupCriterion CampOut = googleAdsRow.getAdGroupCriterion();
				ReturnData = ReturnData + clientCustomerId + ";"
										+ CampOut.getAdGroup().split("/")[3] + ";"
										+ CampOut.getCriterionId() + ";"
										+ CampOut.getWebpage().getConditionsList().toString().split("\"")[1]
										+ "~";
			}
		}
		while(!next_page.equals(""));
 
		ReturnData = ReturnData.substring(0, ReturnData.length() - 1);
		
		return ReturnData;
	}
	
	// Delete PageFeed
	public static String removeAssetSet_dynamic(GoogleAdsClient googleAdsClient, long customerId, String data)
	{
		List<String> list_of_groups = new ArrayList<>();
		list_of_groups.addAll(Arrays.asList(data.split("~")));
		String output = "";
		long[] table_ids = new long[list_of_groups.size()];
		List<AssetSetOperation> Operations = new ArrayList<>();
		
		for(int i = 0 ; i < list_of_groups.size() ; i++)
		{
			String[] values = list_of_groups.get(i).split(";");			
			String page_feed_id = values[0];
			String table_id = values[1];
			String assetSetResourceName = ResourceNames.assetSet(customerId, Long.parseLong(page_feed_id));
			
			table_ids[i] = Long.parseLong(table_id);
		
			AssetSetOperation operation = AssetSetOperation.newBuilder().setRemove(assetSetResourceName).build();
			Operations.add(operation);
		}
		AssetSetServiceClient serviceClient = googleAdsClient.getLatestVersion().createAssetSetServiceClient() ;
		MutateAssetSetsResponse response = serviceClient.mutateAssetSets(String.valueOf(customerId), Operations);
		
		int index = 0;
		for(@SuppressWarnings("unused") MutateAssetSetResult result : response.getResultsList())
		{
			output += table_ids[index];
			output += "~";
			index += 1;
		}
		try
		{
			serviceClient.shutdown();
			serviceClient.awaitTermination(5, TimeUnit.SECONDS);
			serviceClient.close();
		}
		catch(InterruptedException e)
		{
		}
		
		return output;
	}
};
