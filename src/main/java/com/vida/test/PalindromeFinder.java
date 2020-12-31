package com.vida.test;


import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.lang3.StringEscapeUtils;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;

import java.io.*;
import java.util.*;

public class PalindromeFinder {

    final static Random random = new Random();

    public static boolean isPalindrome(final String string) {

        if (null == string) {
            return false;
        }

        char [] chars = string.toLowerCase().toCharArray();

        Map<Character, Integer> charCount = new HashMap<Character, Integer>();

        for (char charAt : chars) {
            if (charCount.containsKey(charAt)) {
                int currCount = charCount.get(charAt);
                charCount.put(charAt, ++currCount);
            } else {
                charCount.put(charAt, 1);
            }
        }
        int numberOfOdds = 0;
        for (Character currChar : charCount.keySet()) {
            int count = charCount.get(currChar);
            if ((count%2) != 0) {
                numberOfOdds++;
                if (numberOfOdds > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main (String[] args) {
        //String example = " ";
        //System.out.println(example.split("\\s+").length);
        /*String s1 = "abc";
        String s2 = "abcabc";
        String s3 = "mom";
        String s4 = "Mom";
        String s5 = "";
        String s6 = null;
        String s7 = "a b c a b c";

        System.out.println(isPalindrome(s1));
        System.out.println(isPalindrome(s2));
        System.out.println(isPalindrome(s3));
        System.out.println(isPalindrome(s4));*/

        final String plist = "sample.plist";
        try {
            //final XMLPropertyListConfiguration xmlPropertyListConfiguration = new XMLPropertyListConfiguration();
            /*final XMLPropertyListConfiguration.Node node = new XMLPropertyListConfiguration.Node();
            final XMLPropertyListConfiguration.Node node1 = new XMLPropertyListConfiguration.Node();
            node.setName("testName1");
            node.setValue("value1");
            node1.setName("testName2");
            node1.setValue("testValue2");
            xmlPropertyListConfiguration.addNodes("testDictonary", Arrays.asList(node, node1));*/
            /*final XMLPropertyListConfiguration.Node arrayNode = new XMLPropertyListConfiguration.Node();
            arrayNode.setName("testArray");
            final List<String> list = new ArrayList<String>();
            list.add("value1");
            list.add("value2");
            arrayNode.setValue(list);
            xmlPropertyListConfiguration.addNodes("ErrorChain", Arrays.asList(arrayNode));*/
            /*final List<Map<String, Object>> errorChain = new ArrayList<>();
            final Map<String, Object> errorChainMap = new HashMap<>();
            errorChainMap.put("ErrorCode", 12007);
            errorChainMap.put("ErrorDomain", "MCMDMErrorDomain");
            errorChainMap.put("LocalizedDescription", "The MDM server is not authorized to perform this operation.");
            errorChainMap.put("USEnglishDescription", "The MDM server is not authorized to perform this operation.");
            errorChain.add(errorChainMap);
            xmlPropertyListConfiguration.addProperty("ErrorChain", errorChain);*/

            /*final String inputPlist = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n" +
                    "<plist version=\"1.0\">" +
                    "<array>" +
                    "<dict>" +
                    "<key>myData1</key> <data>NOT_SUPPOSEED_TO_BE_THIS</data>" +
                    "<key>Command</key> " +
                    "<dict>" +
                    "<key>SkipPrimarySetupAccountCreation</key> <true/>" +
                    "<key>SetPrimarySetupAccountAsRegularUser</key> <true/>" +
                    "<key>myData</key> <data>RHJhY28gRG9ybWllbnMgTnVucXVhbSBUaXRpbGxhbmR1cw==</data>" +

                    "<key>AutoSetupAdminAccounts</key>" +
                    "<array>" +
                    "<dict>" +
                    "<key>shortName</key> <string>accountName1</string>" +
                    "<key>fullName</key> <string>accountName1</string>" +
                    "<key>hidden</key> <true/>" +
                    "<key>passwordHash</key> <string>PwdHash1</string>" +
                    "</dict>" +
                    "<dict>" +
                    "<key>shortName</key> <string>accountNam2e</string>" +
                    "<key>fullName</key> <string>accountName2</string>" +
                    "<key>hidden</key> <false/>" +
                    "<key>passwordHash</key> <string>PwdHash2</string>" +
                    "</dict>" +
                    "</array>" +
                    "</dict>" +
                    "</dict>" +
                    "<dict>" +
                    "<key>SomeKey1</key> <string>SomeStringValue1</string>" +
                    "</dict>" +
                    "</array>" +
                    "</plist>";*/

            final String inputPlist = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n" +
                    "<plist version=\"1.0\">" +
                    "<array>" +
                    "<dict>" +
                    "<key>myData1</key> <data>NOT_SUPPOSEED_TO_BE_THIS</data>" +
                    "<key>Command</key> <string>commandValue</string>" +
                    "</dict>" +
                    "<dict>" +
                    "<key>SomeKey1</key> <string>SomeStringValue1</string>" +
                    "<key>NestedDictionary</key>" +
                    "<dict>" +
                    "<key>SomeKey2</key> <string>SomeStringVaue2</string>" +
                    "<key>Identifiers</key>" +
                    "<array>" +
                    "<string>str1</string>" +
                    "<string>str2</string>" +
                    "</array>" +
                    "</dict>" +
                    "</dict>" +
                    "</array>" +
                    "</plist>";

            final String encryptedPList = "<?xml version=\\\"1.0\\\"?>\\n<!DOCTYPE plist PUBLIC \\\"-//Apple//DTD PLIST 1.0//EN\\\" \\\"http://www.apple.com/DTDs/PropertyList-1.0.dtd\\\">\\n<plist version=\\\"1.0\\\">\\n  <dict>\\n    <key>CommandUUID</key>\\n    <string>a26ea486-570f-4d83-9442-729a1dbab8e2</string>\\n    <key>Command</key>\\n    <dict>\\n      <key>Payload</key>\\n      <data>MIAGCSqGSIb3DQEHAqCAMIACAQExDzANBglghkgBZQMEAgEFADCABgkqhkiG9w0BBwGggCSABIID6DxkaWN0PgogICAgPGtleT5FbmNyeXB0ZWRQYXlsb2FkQ29udGVudDwva2V5PgogICAgPGRhdGE+CiAgICAgICAgTUlBR0NTcUdTSWIzRFFFSEE2Q0FNSUFDQVFBeGdnRnpNSUlCYndJQkFEQlhNRkV4SFRBYkJnTlZCQU1NRkhCeWFYbGxjMmd0YldrdFpHVjJMbXh2WTJGc01SRXdEd1lEVlFRTERBaHBUMU5OUkUxRFFURWRNQnNHQ2dtU0pvbVQ4aXhrQVFFTURURTFPRGt6TURJek1USTFNRFlDQWdVN01BMEdDU3FHU0liM0RRRUJBUVVBQklJQkFCa3cwWEVCT1NzVGRvVncvZHhneXc3SE1FSEV4U0V0b0JUN1Fiam9uRVIzaWpMMFpjVm9JU2JKcy91Y29abUhSQ2dSK0VEUTBqWkJvTUFDZ1hxQXBnbWZneEhQSUozVE9JOWFGYUh1OFQzV3FkeExGRFMzT1NCUEFHd1poampTSFQ2SmtpV05ESCtNMG1rbU1TK3FaNXdzalcramlUTTdEdXFDcE5LdEl3WXo0S0tzMy9tSFFBY254S2k0N3oyYXcrbzl2MXB1NVBuNU95M1JwTUQwVzZLRG1xV2ZvcjY5Tlg3dXdWeG11dTIwNTBPWE94WVYxUm8xQ0RKQURFY1Uxb0xBeEZkQXQ1Z3I3b3gzVFhxb0hnb0tPMVB2aUpJdS9zSmFCZWJBdDErMFRzUThEUjZBQkNvVVZseUQ1SEw2M3NSR2dVRFhoOVE5S1JMRUZwaVBCNkl3Z0FZSktvWklodmNOQVFjQk1CMEdDV0NHU0FGbEF3UUJBZ1FReUVPNnBFSzF6OFpLT1hjcWZ1a1lrcUNBQklJRDZJVG1MV015VmVFUlpmV0R3bFBhaUhnL2FjUUxhWU5IU29DdDNKeG5ubkdwSDVvOWVrZUhJTG1xVWc4S1BMbUhhajJZcmJ4VUhac2J6Z1hkS3B0TGkvSDl1YmhXTnNVdDJFODFNaklBL1YxTlNrYkR5NHU5K0k3UUtwQ09CdENudWtoRFlOdEZJZlZQN0VkNjZ2TGo0UjU1YTV3d1NWanFuTnZtL0R2VHdUb25KbmVqc2JEVXBNdERrUHBMNVdxNDI5NWlyOS9vZkVQYmZzcU1tTXU5N0NYbnBXR2NPTmVDTEdGT1EyYlFuWXZ3QWZ1WU13U2pKWHl5dXpYbk96dHY5ajZCZU92cmlCSk83M2RUN3VpRE5BT0tSdWFneDJaN3ZiZ2pscmhRejg2U2ZNQWo0UXpOSnBnYVA2blloNWZjWlU2TVlScWtENGp3WWYrS0VMQ043eUsEggPoUzQyWXFsaGlOUUM0dWRWYmFtdzg5b1dSckZidk9jVWRRbTlwYk1OZ0lwWkJoQ29iaURLbG5odjE0TnBBdmJBT0Y0YmhJdmZIekZUeFFZc0hYd25seFd6Szc3cklPZ1NFeWt2UTE5cmNLbWJ1SjI0MzE2SDhIQzRLbEs3c0RNK1VFV1g5ZXhDT3dLQlF2MXJFTEQ5SmhaY2RWSHZtbE11Ny96dFJ2ZmNrMWFTU2lFc1pPV3kzb1YyWEcyU3hic0hBeGZXdGNxZ3JaMkRVRWd2cjZXQmlyZUN4TXdhLzVrT0RWbmNXNUx4bkhOUkxEdHBEQXFDUVNtbk5GQU9WVi9HWmVHekJGRU96QzBBNzVLenppWExvM2YvK2p5eUNkTFE4SlJadW5FMFhtWkV4TWErL3lDOXBNbU8yZVRBVXRodDVPSXhuS2FNYkRvN25sRFd3VVZnU1B0NmNQTU5GdkYzNlp6dDF3SmNodG1qVVlrK0owNUkyeDhaVUw5N1RzUHlYNisrSXdQOG9wR2ZHVWFKVTFVVkZMWDlHWTVjSGVqekUxc0dRTTlZYkxXdmFVS0JSdTFQSFpFY082a1lGczkvQy9CRDF4TzRURzhCOUFXSXlOTmM2RHlkYU1xdEFtSVBUbDQwS2RCQi93azNWa0Nlc3h5NG9weUxUYW9QQWszekZ4MEFFY3EvZEwwWHNpT1k1UmZ0UTFGWmdVOFhGWCtVaTdYcEVqR2NvN2p3WmlGY1JyMXBPcmI5OHFnSXlobHJPL0UxVUNJZWgvYklUbWpqd0NFRDhyWldjdTB4MWg1cHJ3ZGxjSkJnT0dWODhHMk1oVkZUQ2lnQlVCTXNPWDFmQ0dabC9SQzR4TmZKYW1NeElTdHlZV2hTWm5DRkNKUVJTZHY2QVJnSjBMNEZ5UjE4QXZoNmtENUY0Vi9hUTBrUFRFcXN5SUxVdWZndFNyWVRkSVl6Kzd5eEtzdWJoeVdkNHFYbjBUQyswdVpUM1IwOXdhM1BLZGxkT2c4K2ZFVEtsSStHelAzMTFjNEI5RC9yMVlRc1JPRTFGcDIxMTVIME1pVTVBclNTYkF3NUZta28rSUZFc0ptcXlZNFVER1BBaVJhc1J5Q3JkMFpkM1BoaEFWZGF0MWErYWF0bUo3TDFNZitNV3Zjbk5GMllFWTVXdjJBT0VHMUV4TWp3eUlLdE91bE1GbXZyY1NMaitwdS9sREZ1S2R5TmRVcnhFM3JMVWkvdHVzZEZSS3hlQ3lqTGZoR3EyTWcvTjBDc3hQU2Jaa3JlUUlHcjRNMmhYSXhiMmlIS213ajVnanJnVzFnUVFFZ2dQbzlkcwSCA+hDdk5ycmJEMjVvVVhTSGtUZmltY2V3V3p2T1pxY25VWTgwWkpJbktxZUhCMzJSMmVIL0xZRjhnZEgxQi9DVmlIcThVWkRMZThyd2tkUjJoRTVkVmplR3FIRFFybGM4YmxXUXFYM0EzUHI0cjZac2l1UDN4MkRsYTJ0andCNjFPbk5NMXRtNHdjdzYxUzBOUEpnV3dsZXdlbDM0NVZ5cnNJUmZ2ZzJmdW84Z2xSNUVXYkxZSSt4QVBPNmRuRDQxL3Q3S3RCU2dKcGg1b0s4ZHRnYmxlK1BXbGtPUFM0Ukk3a0dvTFJCZm1QUkRlVVFlR0cyaHFmZjJIZXA3VlR6RVNxTDB2bCtZWlVFMlN5YlJEQVJEMjRtMGtjZkJ1QXlGZEtIclJscTRRS2dHQVFsWUtWenFVZlhkR2VGY2ZEYnczdWd2ZjZkSi9hVElWZDlyRUE1OHVHdmc5b1BBNk41Vkxjd1I4U0E5WkRLNFdldjNOdTlKTFhtUVZ5THJNOWJPZDlMbmlKVjZyTHFQakRXb0lwVjBPaWxoRGptQXB5ZmxHOC8vVUFCZ2R1SUo3UWpOd2Q2eEdyK2t2eEpwRE1ISzlwR1lUN281UXpxRnNYb1FCT216eiswT1pFY09nRFFhOWN0MDh6ejVNRFNJeXpUbStyWG1vZXhQWjMwOFM1a0JYTHl3M0dJVmEyQkVMdkl5cjBRTEQzazRmZmhTVWE5dVhhQmo2TVdlZlJuU0xXL1V4N01paDZvRktjVGpNZ1Fzd29EMEk1d0dna2N5eGUxeEdxOURmdkNPK2RpdjFYRFRlSkJNZE05Qkd3SEhmd05kWTJGWFBEcEF6RTRwSHRmaEVLcXZibXFEVEtnc0p6ajBCVXM4RlR4SHFldXlGN21UaWpBNjNuT0xLUjJZTFFrYzZ5RHY3QjNLL2JWK2dOQ3pkZTFrcy9qSXBTcktneU5XTmhXNjc1RnplazBVcjB1MWJUc2VNaHViZEhaYVZRUVFvOGdOKzdqQlNhTG5kVlN2ZDBSM3VIc21BalNHb0JMSzhLN1c1SFFtc25qcXdYSXQrSUttVG5MZkt3dm9xSjBEcC9XWlJiRVpFaEx3ZElTZWVNWmRpWXJmZW5jcnF3RFhFMnpmMlI2Z2YwRk9mT2h4MUVyOUF2RFI5WHYyeWJuSUVxTDVNeUNzOHJKSjNvRDFrNjdWRGRYYUxDbTVVTnlKNUZwdWcvV000aDRwRndRUE5BR2QxV3ZZQW9OQ0phSmJVK0tlSFRFa1RJVmVtQ2N6MTEvbEhFQUExaTRybTlidkhkV3ZVYkNxQWl3WXNXc0tSVGtWWFdwSHJFBIID6EZ5WGw2M3BHYWZDZWJCV3ptbSttZjlxRzBmTnVmQk1wdlJRUWt2NEQ2Sm0yVmMzK3N3MEIxWE5wb1VpWHAyQjR4cDlPZjZ4T09Tb3h1T3dLS1RNNXV5ZTdONDF0RFlvN1NMMkhkNzJlbzZUTy9rZzBFUFRGVEpEUFlHcDdHT0xwK29URWQ2SW5VdjRrT1lZZHhVNE9lZGR1WTQvdDB0SDltYlE3cytFVHBFT1lOZWVVaWdMTXNCNGQvRy9DKyt2UFpoclR0a3RKZFFHY3JHUHYwbzUvK3d3RENuT3orY0trM0pZaEYzU1pBdXlwcUVlSU9sa203K1hCT3oydnVKZk9iTVJqQnF6em5FMUl6V2tmTVpQV3paQUkwalBDaEhkdktYSDZmQU92ZjlFYUdNbFVILzRvWjZzZ3d0RDdJaDBRb1haU1hObzBlVmdTQ0EraFYwblJ5SXpOY3RGUVY3V1ZsSFlkVFA1RHl2Z21UZG1vZnRENk81aVRFektJbkZESS9sc09jMzNRa0tSdk5RWnpvZ2d5YUV6QlpUU0FZbW9xdm9IV3QwM0VFVWM5Zno4WElwKzNLQ05pNENJNVRQMjFuWG1TbGVTVHhyVVl4dDZTK3U1QW9rL08yUHZYaElRVWNjRFhRTUxGNWkrUUNnbGMyTVpmb0VnS2Jwc0pCNzM1bE85cGFqWGhFQXFIYzBqdWRVbnJIMHdpUE9RdFBwcVE5bnZNcnhKelE3MlozY3QxWU5MOE4yVDNsak9hTU4wYkJuQko0eVh6OC9XV0cxWG8xWE9FSGx4b0pVV1BzWkd0akpoSzdyUEY1RENCZmxWSFJqdDJ1eDdoem45aW1QQ2N4bzY2SGhMTVB3NE40YjFueksvc2dxVFgxdzlNTk1NZldmMVJmd2x0NHNPcmczdjNyODZySFlDVXJnSFBEcGQ0M3JaNnk4RG02Z0o1eXBzRHIyYXpMNDRmNnMzMVNSbDZnaDBVSmpkcnBrcTJxYkN3T0NFRUVyYzFwUGhKSWJVSlFKL2sxWnlGYVcrNndPa2p3OXhqMFRyYWFzcWw4RzkreFFBVkNMZWljK2drM1M5aUorWjRTWHI0ak1rMEdlbWZaVXEzRFUrSEN3dTBTRUpqTzA5ckFEdDl6RldEd01YUXBqNnF3b0hxSFg0N0JpVkF3b3ErS25WYm5mNVVleWlPaXRjSkJVNXgxVnhHM2NaK1M4dHZGcUppME9rcnp4aFNQQ3BRSTZCYjNvUlhqbVR2Zi90akNxT2srQkFKWGd2dTY4Q0V1V1lmN2VDRElub0prTVZjM3dGL0xtcElta3FuWlNlZ0wrUWwEggPoM1ZIeXpzU2U2N1FMYlFLSUZDVGN1d1ZhTVF6c2pLRlNlb05pV0E3ZTlQNE4xa3VGeVlBL3NaNHFvdFdQdjZVKzlDNkd6ZmtPajJIeFNCU1F1SXNtQ2R4MHFXSTNLbVRCWk5aUVZYV0dVS1RMSkFjVkdYcXNUVmI2aC9xNFE4MEd1ZzlpOWJ0QWpvV2xYU01QbEx2SHpUY0tEZ0RyRTdQRFlkUTBJMDhGQVNPMHA0Nmkwc25MLzJmQlBRejd1MDlMTmxPY2RHV05FNmFkeXR1Z081TFJacFUxOTVhOUdCWXZBMkVzZkdIOWlCaE96bnQxQWgwS3NVZ0lkMHdHZHo4bzZFdldtTlNaTGc1RkFDdFpLK3h2bVNNVnNTRGJZalNhcWE2VVhTT05rWEIxRTZ0eDIvZzIwY2pxV0lIV0lhV2NDQ3RBeTRpNUZUM0hXNE1Ycllzb1Q1Q3V0NkozaFNUUGFQRE5HVEY3TzAyWjRGVDVHWStDVitldjJpNk9HMVN2RXMyd0t1NEV5djZqMDhkY3JqVmxBbXI5RUtvUzhKeUtWd1VYalJmdzNpRzZSOHZPR1F0YXFUUEZrUC8vVWZ0SWFDNWNiWUk4VEcyaXhoUFY2QXJKa3JZak9ORG4rMkdaempXRTlJR2lKZkRQSDhEL3c2UmdkZHRlMW15RE9YUGZyQlVqek1od21BbEtQcko0WDJ4ak5vWDErTDdDTmdhSnNRSlVYVlplSHhjZEdHUGh6amtNays5NG9UMXJQc1d0SnFIeVd1RlBlUWNoYzNyRnA5Nkt4VnI4TFQ4UmFteitJbGJEZTg4S05SeXVoNkszb2Q5OEhBYXNnOVVkZytjbWwxZkphbE9hd2dtdEhlaUt5VjN2U1BRRlc1NmVrQklJRDZNbUh2Nk8xY0pCYWxzRmRFMTM3VGVuWFdJOVAxMWJ5bmZ5d0tUS1FTMU9FU2tyY21QTnJWZVpRcmxFUmg4T2VpL05nczVhaStoQisxZHN2eG5VbUtwVFppdnZTTStyYjZCRmZXdFRqTW95L0xpUmQ3N1JCVFVQejlIODBXbU81RnRVZjk1VEowVUVETUM5dmR2SWM0Y2tpb0FWa0V4K2xVNGJ3Tm16Z0FtWDZYdGRuUzJ0MVBMTkFnV29UZ2RGK09QT0dUZVkwcVR5d2VTTEtOUDQ3dXNPY3ZJa0ttWmFrY1FHNk4rWVlKcU5vRUVJaWFmRU82K21UNTExRVJUcUIyRno4ZDY3M0o5RVQ5N05QblZRMXgweldJU2I5RzJBNTFaTStLZVZGR1FPVUxzaDVCUWg0UCtLblJqd3p5MXBIYnF5STdJSgSCA+hodlN0UEFpQytOb0N3RGlSRU9EOUl1aWZEUXdYWC9yOFM3R1pqdkp1MDhJVFpsbXM2RGVyMUdLdjRnVS9IbjY0akIyMVdIRVhrNWM1RU5nWVJ5ZmRPaUlOTGZLVkhrRDUyZTlML1Z3WFhjOGdlN1d5UUdubk5TVmlac3g2WEF4Q04vc1JQY01LTUg1SU81Wk9vcFBpR0RNZndEMUhmRUppRzVsVUVNVHFtcE1vd1hRb0tkSkpjclBFNGJzNEo1STdJWjVwMEljcnRXMS9XaGt1T3FkVUVXa2d2WGViT0tnM0pDSzFiVU1TWHRTOEQ5OHV1T1FKRk1sN2ljUk5tMEp1eDNHbnlZQ3VrTGdqeHFrYUIvcjhadkQydjdOT1dmMnpDdElwbnFxS1VPanIvcDU3V3ZmeU5zcEgxZXNWdDRuOFRVUXltbHVqcTQxK3BSRUNBRk5UcmJSTlRLU1pKcVZKZkxhQ0NiaytPakFQaDViUUJsNHpiSTgxSzV0c0drSnFsQnlFdlNZeG84ZTNvZXRXR2dqQUxlSUZ5cFZmc2VvRDFodzY1WTcwUDQwbUZUSDBWOEJvWHZQRlhoQndSc0c1UWgzN2g1djF0bnQ0eW5hTlNKQTNmcENCbFl4Q0Y3cFV6QmFpUTE4VXNtUGwrNVN5UW5vV2xVL0tFeWdXYk41T0FKbVN2QnhOUHN4L1F6RzNjSVVXR1Rad2Y1VWxNMWJRcEtmbWJETXJMNU1seW1UUHUrekJXQTBlelpxZDVDendoQi9ZZGxEdC9vTEV1ZW1Ha0FjVXV6eVRSRnBRcDVuYkF0NGM2Wmo1UFlHVUdTSmpzNjhMY0pFRFM1bXg1QVI2V2E3Z3lMTGc2VFkySzUreXVRZUg5RlQ2d3VhckIxZXhWam5taENwempCMzBHNUszR2NRdDkxYitDYVJ4RnBmZ2dCTWhtTVVtd3libWNRY2FTSzhhVWNuWnFxNVA0REhYVTM1bGJuOWRjSTNlZnhxKzEwLzU5NUFRcHdva05OQnVGRkVjTmU1NnE2UW90cWJnMU9VZjZFYXNQb0VTNDVQT0Q3RVVwL21rK1BnSy9BOTR4cVFoVGV0YnN1WURoUHkzMDF1bXptV1ppSVRGa2ovbUlTcUpNYit3NTNBUlVvSFcwSTcvNi9XTG9oUE9tdnBVV3FqZENNMVpRdDMvRkJCblpVSGZFL2huRTFZcSswZ1FsT013dXBSTUlWNGVldHJ2WjNZZDhvS0ZVdGJCd0E4M3JtMXBhbVBPQldud2JqQ3JXcTY0SzVCTmhuK1B6aDB6a3QrMWtDbVpEM2lIVlVDUm8vNlNkYys4BIID6GNYYnZDS0hRRWdnUG9zWjFadFZxcFNkTzBpVHhQdVZ5Vk1nRzE0VVlyYWxoN3FkS2hMb2ZsY2pyejBnV2I1M1Y1ZENnN3M0cWViRUkrWWNlTWFEa0M5eWkyc2Y1NmFjTnJXWE4xV2poc3N5cnpQbTZTdEd1UllFOUR4UjlyTkhRN1Y1NzQ0QWZMb2NXOEFnVXdrREtzMGwxWmJBenRjNHpBM3BsZFhPU0xSbm5pRm1uUFgxR1Q4MC9PMWhjZWh4dm5IdVNxMVZEeGF6Q1ozblJBSDdNWGFWbnFwclBWSEtIblV6VG02SmVnWU4yckNjZWxET09RaC9IbEFsYmtOWjBrbDN0N1g0eVR1bE4yR2IvMTh0WjJLV2wvOHAwN25WaUtQTWFBVHZFMHgwcWd3S2ZqUi9HcWlNMFR1MjBCai9TRDFZaTFycjhqV3lKVTlObW54VzBucjNzeVNmZ01aYUJEZ1p0M2U2L0RFaUVZQkU3djJOV29tbEtEWlVHMHBiaStUMHBtK1N3Wk5nM3BWS2JJbVNuQXNaa1JmdXhPcUhJc215S1Z5SkE4WXA3aCttcDNzVHk1QjQrWTZHL0lXaXJrZ2tkTnMxTGduSXVNcStaTnBMTHoxSjUyQWlOL2xOUWRMdlBpSk43WHBFdTFVZHRqV3RmYmtUSWRQMC9aNC9mZ091M0JBZHVlYUt2RDBpVWFLcnpTeTB2bEFpNE4wd3k1aFlIVWtRa3lhZldVd0JDMEZpWkR0QVoyVThtbGUrcHE1K2EzRDF3bWlnMURqUlNXWmRiY2dIeTE5amhndE94THhrN1Z1Q1hYQ25LbjNZSSs2b0tmWTZjdXAvWkM4WFhnOWJFaWlPVFJwMFczRHhjRnBhNTFESDhKaGdFZFNzL2J5TDVrV0djYlBBcStjcGNZeWdaZXhsbmJFTHEzd0xsbnhhUVoyeENINThpWVRDVFFhL2EwNXdZUlF2T3R5QnAwZndhcXErT3RQU0R4UjdCZTNLTGNwUSt3S3dEQVl5aWI4TjQwMk1xZFBBUGhhazVodUVBOEdZYlphbEZuOVFsNDZpWHNZUGdpZklwVExTTmtwNnR6NzA0NExUemJKZi92NjRxQ1ZVTSt0VmprVVp4MlJ4SU1oWE56OHpqYy9DVUdGYjc5M3I0SDRITWhPRWRXaUJ0MWlHWllhQXNIK25tclBVOWpwUER1a1g1bUlYM1NPZXRXUWxCMk4rTTUrZ3RqNXNGWiswL2MxQThsVlNSK0p2VjNLL0FsMXQrQjJRNHRZeURYcnBpVzlBd1hhbjZxaDREWGtqcW5Ra2xDeVE5SnppNWJQbzcEggPoTUFFTDFDaXdQWGpzMU1PWmRwVTR6bTZ5Wi8ralQ0b0RSbHNkWS8yalZ3MXRJd05IekYxZUdRTml6OTVVNjl6YkJSRjNzOXNaa2E5S0k1SDNzbXZZNG9YYTVpaGtxUU5JcDBLWVdWY3g0MDJrTkI5RE5KcjA1OCs1YnU0SVdkTzR6cFpaUDRodUFUOCt1UXl3aFJjeEw3ZUVEaXNkQVBldytVSlF3SmFWUlBsY2UwNWMwNzVJSzI5L1VhdGNEcXVIY0E2bEsyT2lKVWliWjUydkpNVUJOejl1K0I0VlFUMXJvTnIzSjAxd1lodHB5VGtjZkhWRlpTSjdaWm1VZVZXMDlDZ3hybUFZUVFTSGYyYVlDMldWMktFL0Z2YjdKeGJLK0Z0cWlzTmZ3c3NUSmtVUnlkeTdTckt0aWxudFdYY1EyR0QxY3Yva1ZIcjdqQldiRXpYN3A1ck5oYmdTQ0ErZ0hrUjdiT1h3d1NTV0svcEhFTmVWNE4wTGxJWkh3UndOVGkvTzFLYUdlcjFwbjJYZHJjdWlTdXhEemt0UXdEc29OYXpnR0xHbmVYTm1xbzh2MS9GYnBnMDJUNk5Zcjl3WmdpWDdQcmwzWG41bVhQM0M2NmVQZ1Z2UFYvNUhzMlpVa0lYSUNCQjk2aGRHak5DZWtaYTVqNWlET1J5MUVZY0oxTTdra3NlZFlyMDFiL1p1RkNWUzdzWE8zTWN5aWRRYy9VZ3VRSHk1dklLemVxWGRxekQ3bFU4TmM1MUs1WDZ2WDJGTXI2MjJ1aUl5cHpNWmgvRUNySDBwdFFEa1oyZjJTMWl0bzluYlBCc2h0V3BGaUJIWCthYjNGMmFndzJ5aHRuN1FGVmhhYW5JdEdsWDdGamVQanl0eDBNZDA4eWg5TE5kUTNKOU80ZnNrN2xFTC9DSlphUUtLMWwxVGJCNkpBQXFscVV0UGdXRHFIbkVibXJzVDlMdE1qN3RpbTVBYzd6MGl5bmFRZWVnOTdNUFZTcjVrOU9ZZ1o2amN4YjJ4dlJvdFFlMjRUMG9FOFpHanM5UVJmNzEwYmpld3o2RFRubGlkZThMZlRUYWs1cnI2OWFzWVN6OFppVmdDRVR3cy9iWnE3ZnZETk42RUliSkdDV20rRU5Lb2I2OHVLVWZDUk5JNEd6VjV6RHlKSVBmR1o4UTA1MGZXVThRNm1qeDdYVWhPK1M5c1ZnSS9VdStVZ09pMGxUN3lrNkdNcmxra29CenowZHBKRzRNQ1lac09pM0w1UFlTTFhvekxiRTVmQXFQSEpoaW5ZNTRERTAydUVMUWNvcFBPd0gzdVEydVBvUTJIa0hyeQSCA+hGWVpjRHJ5TzF1UmVQaWl4ZjdsY3RNY2pweUNScXJLQVNnNmZBejFkQ1MzQ2FYazB2U1MydW5IK0o5Tlgwb1ZMWURoR1ZBWWVyaWdCcmNOTXlJdUwxU2pwZHRpbW43b2plRTZ1RFdtS1hjcDkrVFVPQTdNaVdPaWczUXR3UWJLT3REck5FU0E4cEN1KzIyNCthc2Z4cmY1bS9kV0hQRjFxeEhDSnhvcHRocUFZNUZOMXVrbjRPblJQdG1PNGRVVW8rblVpcnZGaE5zWU1wdlpYWUtDei8veFp3eEJiWVB3UWRsMU5XTGpyM1R5M0U5MjJHcHcvM2JkWktlQkpWMGp4K05FR0RmYnpmdlZHVkxwQ0EwMGNxSFUzZjQ0R2ZQSFFOMUwwQTBuVmFxWjVFWEZ3cUE1RE9VblA1MC9mbkxTT0piWVpkWVlvVnpaZDd6K1Ara2NydEZpNWJqR2pLQzFVSHp5bkFzT2hRYkZ4YWxKbU9lVnptdkh2K053MDBRa2tHdGc5QUJvRzZlbUJUTzZGM2hRbCtLZUF4MW5pYTRRd3RhWU1WYnA1NjBtQ3ptUVlxZTNyQ3k4MW9obnhURFZuWFg5clBMNE1nV0VweUNVNk96U0dRNjY0QWtFcFJYTWwwSVdVVXFZaHdVYWxTdEZpdk5BM1RzOUtOT3dYNHZyaGZ0dS82WjduM09Xa0x3c21PZXRwcm1RVDNmelI1dkZRSFdteVF1WXdzZUd3U0Fhb0ttNDZuRWliMW05MW44ZEZYZURMNnFETSs5a2FkQTJncVlnRTgxY25DSU93SU9sSHVPUE5NT2pmeUN0TzBwNzhLSlVTcmtqYmF5b0F2dnF4QzU3MVhNa3NjQ2REdzN0aFAzeHNGOU54YnhqTkRYZHVMZTBFdnM0c2FGZlMvQklJRDZFRmJQZVEwZXJEbDQ3SDBvRko3NjRYZ2k4VDhqK09TdmdtZW1HTHRQNkQzTkx6d0ZZbE9xM2FlQVJIZW5NWTBSTUZHd2NybU04dnQ1ZnRBZTNBb2NQVytUeWxpeTVCajFlczhrVmU5UmVwaDk3OWtMWnF0Vld5RVNiV3FJSVZrRnNHUTA2U0txck8wRW05bWVJd1A2U1pON1VDN0hyZXRZaDdFWEZMTldRcTMvT0dhclhORmI2eFpXWDl4YmF3UC9vQS85d0VpT2tITXVUVHkvZzVZdzhPWElYVkg5eE85QzB0cmNaaXZFSkdKVTNSdFRpWlhkOVF2bW16ZmFkcDY5bFR6djlLejJRMWNhZ29FdndQVkNJc3J0OGphTlhPTlNCOTI2RUIvZU8wUTZKTXdDMGZmK0hqBIID6E9LZUpGNTIyL1d3RWY5Y0FWWDFXNHJWNDNnc1JCOGJNS3o3RzVybVU0UWVVS0Y4NFg1YWlXRnc4YnRYRjZLMkRQY0V6SUlOWXJhVk4vdm1UaTBxMzI2eXpwWjQ5bVZlcHhPNEJHcERJdld0dWRPZkhuV0wwaU9BWTRCejBrc0ZLeDd3a0E2Y21jbFhGeDlJMmtQRU5IT3gyMTNZbFVYbitxM1B1Tm9QR1dITm9RakMzMDZ3RzFsU3RUQ3VTR3dIRE9TU1J4eXEwZUptRU1xMFlCeGhPbkFPRjBLaHEvUkpLNWFGK1RRU044TllXVnJJQWNPQWhDRERHcElZZHhhWVd1UGNGcHh4NkNBZjYxM0RiRyszK2lLM0I4Q0N0RDhTWCtwK1MxS0x1RnVoTmp4RjRCWjJqVmZGdFRyL2VsOFQ0UDdBdEo3YkZDOG9zazY0a2wzZ0plNkhxS1QrcWtWU2c1Y1IvZThtaERJWmlYWlB3ODVCbXovN1RGUTh4TGxMZnRsUElHbk56eEM5cGIzUk54V2N6VnNJS2ZnOWdMejNKbklvOWtSRkEwNE82ajM3djV6dy9MT1VBMERyRG9KV1pLai85OGVCMktBbW9lb1A1RlBWNUF5UkViY3FmaXhNaXQzMkk1MWFQOU5udXFjMkd4S051Y0ttek94SzNGSlIyaXZBTTJvdHdIckV3UzdEdWVsdGM4Vm5HRjdWRUZyY014YlEyZ0VsUWxxZ1R2T0xEVW9oVzEyM3dPV2NUSGw2RUJ1Nk5XbHNUcGcveXdjOG5tUGtKRkYwUXFmd2QzNnJYaGpocHNVcUx5Y0tDRnhvdzArOHE5K1k4K0dGNVpTaTYxZDRLd2gzRHoydHdkUnhudHZya3pET3hFTDc3SHdUWHpkNDhRdTBwcEFDelZjMFppRStuYnY1cjZ4SnJNMkVRRUhYVFBOQU9PSVB4MWN5TVgvTmt6OFVOV1o5eWo3dEpMWFJmWHRRd01GdzJHK3NTdUpBQzBQTjMzMSszZWtNTjhvcVYrblhVWnNESmlEVmdKMXlZdjJ3c3VIaGlIT0M3ZWg5NER2RFZXK3R4cVpHMTBINVJvbGR4dXU1NG5XendzbWlHYUlPRGRaOFJ4Q3hPUEw3VzdNVURBZnBzR1Y0OFB5NndzZG4wRXVweFJ0QzhIaE9ScVNHNExzTWVLYll5bUVacm5CNk56MzB4UnlTTlJlZ2pKQU03RzFjU2Jqemo3QkhabnFWY1ZieXA0UmJ3VjIvNEVieVJvMnRXbGR3dmpjTGZtaE9QeEhBN3BUNCtZa24ySU9nZTZOcEh0U1kvaHRndEliMDcEggPoS0dlcndoK2lsUE1raUc0UWFKQkVsaThrRWdnUG9BZ0tJVzRNOVZPbFg4MWZvWk4xUVE4ZVBqUWVKNGc3RFVKa2RxcW8xVGs0UVB0d24yK3E5bVdNVTNZUEZDZjV3WW1wdTdyVmNyVGsrL2dCZGZvQkI5VFd0dmIwekh5ZGpGbTRONEpkKzdIOG1NSjYvc1dUUUVBZUI5cE4xYkhqa3NOUHlQRkE5cnE1SVVxdTZGTk1iOCtrUHVUZGx4VWVIeW94MVlvVG5uMW91dXhuOXhNRms3UDVuczFTSm5iMVBzd0VvUVZjckgrM1ZFNDdFeTVZS092Uk5TTE42K0FDcmtldDFyUkR3OEROUFZHNVhJZHB6bTMrSjJoSytwWFgzZGNRK0l0aU1XM0FSY2RiMEY4V1dvSmEvajFkQW9jU0xZS0xIcWd0S0xmSVFWNmRaSXNuVWNFbjcvU3dqZk1lT1dNN0FiMjVtM2JZamRVWHRpMk8raTdjcDlXN2lRM3FDNDNXb3JMSFpKZm5JcGpGdThkY3Zma1YzcXFQYkE1UzQ2SDBSeERwTVpWeXAwdm5ZNTJTODA1aDBsV2tiWUNpcnp0VTNwSk9yODFQWDZwMGowRUJFZnZobnFPcHJCOGRlSVpFelBScGtPRXdYeGx0OGYxMzE4UTZrbHl3YkRRYURXUWl1aGpJellpVGpvODlQMnh1bm1Md2lzdmc0OW1UaklqSDgxZTEyVGM1WS9nUlhyY2lvMkpnZ0FIejEzcE9HWW5PblNrL2pGWkdQTFQrS2lEVUwrek94eVZoeWRMbXVxaXFpbWc1d25kUWV4bG9DZVJnQnE4QzExVit0UVhuL2VvTjF0bzZueVdkQ216Yzh0TFU0b01EdFdmem9wZFVtRm5VRUh3WE9CYTVNTjR6dHpMK29HaE01YnJYclcvV2hWWFhpQUQ5SUdGcGF2azRpd3NUTlFCcEErNTRYMWZOZjl0TDJNNHprYmIwZktnQU11NUJUc3pVU29uRW5MWXpyMU9ockN4Y0ZyWStjcnlHcGF0ckRzQnRSaHI1bDRHSEtPYkwwUHFiQzRtdjZVeUtEeHliWXNlcFVJalYrSHBjMGtaSGxtdmE1Vk5LZEt6T005YjRBRGJNRmczTHBmaXVGalNrREJQODl5aldXa0xsVTRITTUvNFdCbEtkODJTaTBoYVY5S05MU2RBZlBQZUxwL1ZCSGM5QkU2UCtBNUxzT0hnQThMU3dkNXNBeTkvZDBTNTk0SnFZS2cwUzZjUmd5NjFHRzRHYVhkY0VqUktkc3RqdHltNWJUakxpNnBBMDQremJCbmRpNVduVwSCA+hycE9jY25YS3JVSjVWMkxuaHVBb3MvVkJ5eGFJV2d0MUl6QU9MY0d5Qnc2WkNFOXV5OUdKcWdScWp2TmhXYlVXaE1VOXdETU9WdGVTUnhDOHg2OEsrekR5eEFKNkhIaDNlbEtnc0l3TUN5Yk9vQXJuM3VNYUc0V2IvZkYwRFVZVFdPbzBPSGNDNHg5MExQaEVSZmRXbFZjcHRTUEZRbVNtNmNJVFlCMGtiVTMrUVBPc2lIQnlCR01jOVZmeWpVdzVCWklibkVDSnNMSk5IZzJobEpmenhUaDVQWElnbC9zblBHZEthb1pQOXpqdkRzUCtxVEFuNnFiR1pmSDJiU0QzdWEyQXdpSXY3OWUzMFNlV1JyQzVKMWRCRUdmcDgyYkIyOThtN3FYMERRRUJJYmgxT3hqYmJTQnBWWE5GaVpTcWZOWlhOY2k0cWZTVlN4bTF3SEdjVW0yaE5qdk5YMGtiN1V5d2d4ZVp5a1FTQ0Erajhlc0hrY0tCaU95ZzlqelF0d3JSZTBBdEJoa3NrRlVMVmdWZ3F0elNMb3l5RExtWXhnU1NleUtuc2txS0NtSWt3S0Q3SXY2cnRMbGs1K2Z0NWxNZ1RJS1pTV0tseFBrUytKNXhDUnF0d2ZZbkg0RmpJbUM4b3JCWEVYd3lnY3drRnkydWcxMzhOcEk5a0hpQlVqVExhekNnaU9TWnZZd212QjdWNHE5TjRIYXhxQytkQytZZXYzblRwSElnS0Z1Qmt0Y2h1L1psMVNvd1Q5V0ZLdzRmSFhvU0VmTzM4eXh5MEprVnZLVm00VlJQdTQyQmEyK0lOQ2N2NDFHN2dQaXBFQUZDU2dEU0xQMkIzSzh1b3dGbyt3VzhDaUN6WkJ0SG15bHVTR2puWTNhSGUwcWlTdHh4VFd3NmZLWTJsaHlva2NMdUlZU3ZYUXM2alk1UkZiandld0VTWEtuQnB6cDZoaDFFWk1IS1FmVXhWVlp3d2NKRFNpUmVBbjZjZzNSWmdkN2lFajErKzFucVZkdVBRNzM1RlhjVFpyV1dpaHlvM2llc0R0bGJ0V0lCMkRYMmRPWTNQenRFMXhaR0NFUUVFK3kwb1dDb1NRN1Y2WklNbmwvRWRNMnJHeHl4T2xWQnV3UGR1ektRZkxIUlJaaVpGSTI5b3owL2N3MDgvdzh3dlJRd21NQUJGNGdWSnl5aFpISlRoQVgzUmp5MzRUTEo1ZFozMmczQ1NmVXE3cjBPWXBBVkJGeHdpcEtIZHFSY010d2VlTnptT1RyU0lEeHlLUTVSVjZJTHoweVRCT1l2YVhUaEpxWllJbm5ITTZHSWxGdHEydlVDBIID6EUwVFZ6QkNXR3czSUkweitUTGFESE5vRnpVaXAvc0tvYWdZNDI2WmYwMk9oUklkQVNvR0VMK2ZDbFF4WFlQOGZwazZ5NzdnUVZvTFVrazZyYUFlR2c4TC9TTGg5OHcxYzZTU01ROS9KZU1qMTN6bk84MUVuc0xCeGVYSThheTdaQjBsZ3dsd3dKR0VWNGxLaTZPSHBHLzV5TzZJV3lzMUw2YmJPQ3ZvdzJoZGVYVUVjWllCcE8yUWJPY2t4ckZ2WFhTRkljdjNSM3RnTjVJZlkzL3VWNjV2YUlBWjlXbm5lYmdMUUhFZ3BWL01IUzl1WkNJcDFTdkxvMW5QcFBzNThRSEpxNXNmcmkyV0dVVG44MkFtTkZBWk9ydzdBL3llSjJKNlRQbndZdDdXUFBzeFg0TGc1cjhZak9nZjJvdnNxVkFyMUxFVW8zMUl2Z3lsR2tGbGFaZlp3NzdLbWlpZ01TbTBicU1xY3NzZGVEU0dCMlV0SjRKV2dUNC9ITWl1UjBwb1RNSjQrSmJCSHo0TnhiUUZCVkRDVENzQnpXekd4V3FoNW9BenFvWG83RGlFYUVXbm8rOTJ3dmR5SHl4MnFWN2dlcDUwRTV0TlFKRkxIQXJwK0Q2a05oejdocGg3K2NwUHE4RmdONVpvMFNiTmJCVVBDRUwzVjloeEhSMmloMTZCaTcva0V2Y2VtdXA1YVNXaW5RaElGbUR3RFh2S2tvNVF5TUZMVTFVMWlJb2F3bFpOd3cyRW4wQlBJbjZHK3hzK0s4YjZRNkdONVVGVGhHWHpJcDkzVTREY2FvOXd1QlBQSHhsNkU5MjUrWVVvOERkcjdRTFhPbFhPVy9INmF4ZzhZSDUwSVJLcTBUUHVnYm9tZ0QzWHNNZXRONXFzTjhNYkNnUWVVa0RVL2xib29ieVdlY2xWSEV0QXhYQkhoRGk0ZWdRMTdVeVd5NStJQ3UrUTNEZGlNWjFKb05YVzNMTDBWV0Q5ZWtZN3RSQTJzZWNQOStFdjNjOWRySlhuSVJ3VVJ1ZmV5OUtSVDFaK2p1Z3Nvb1A2WmdmREZrYkRDdnh6ODNUL3hHTE93ZFh0VnA3S2NRWEZ3RlNhMVBlUWRiRURwWGozYjU3QTZTMmptL0tYOHVXVTh0cEJJMng0NEFBQUFBQUFBQUFBQUEKICAgIDwvZGF0YT4KICAgIDxrZXk+UGF5bG9hZERlc2NyaXB0aW9uPC9rZXk+CiAgICA8c3RyaW5nPkFjY2VzcyB0aGUgQXBwbGUgQXBwIENhdGFsb2cgdmlhIHRoaXMgV2ViIENsaXAuPC9zdHJpbmc+CiAgICA8a2UEggHZeT5QYXlsb2FkRGlzcGxheU5hbWU8L2tleT4KICAgIDxzdHJpbmc+QXBwbGUgQXBwIENhdGFsb2c8L3N0cmluZz4KICAgIDxrZXk+UGF5bG9hZElkZW50aWZpZXI8L2tleT4KICAgIDxzdHJpbmc+bWkud2ViY2xpcC4zNTgwOC4wPC9zdHJpbmc+CiAgICA8a2V5PlBheWxvYWRPcmdhbml6YXRpb248L2tleT4KICAgIDxzdHJpbmc+TW9iaWxlSXJvbiwgSW5jLjwvc3RyaW5nPgogICAgPGtleT5QYXlsb2FkVHlwZTwva2V5PgogICAgPHN0cmluZz5Db25maWd1cmF0aW9uPC9zdHJpbmc+CiAgICA8a2V5PlBheWxvYWRVVUlEPC9rZXk+CiAgICA8c3RyaW5nPmIzYTYyYzEwLTk4ZTQtNDc0NC04ZGQ5LTg2YWU4ZDEwYjA5Mzwvc3RyaW5nPgogICAgPGtleT5QYXlsb2FkVmVyc2lvbjwva2V5Pgog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</data>\\n      <key>RequestType</key>\\n      <string>InstallProfile</string>\\n    </dict>\\n  </dict>\\n</plist>\\n";

            /*final String inputPlist =
                    "<dict>" +
                    "<key>myData1</key> <data>NOT_SUPPOSEED_TO_BE_THIS</data>" +
                    "<key>Command</key> " +
                    "<dict>" +
                    "<key>SkipPrimarySetupAccountCreation</key> <true/>" +
                    "<key>SetPrimarySetupAccountAsRegularUser</key> <true/>" +
                    "<key>myData</key> <data>RHJhY28gRG9ybWllbnMgTnVucXVhbSBUaXRpbGxhbmR1cw==</data>" +

                    "<key>AutoSetupAdminAccounts</key>" +
                    "<array>" +
                    "<dict>" +
                    "<key>shortName</key> <string>accountName1</string>" +
                    "<key>fullName</key> <string>accountName1</string>" +
                    "<key>hidden</key> <true/>" +
                    "<key>passwordHash</key> <string>PwdHash1</string>" +
                    "</dict>" +
                    "<dict>" +
                    "<key>shortName</key> <string>accountNam2e</string>" +
                    "<key>fullName</key> <string>accountName2</string>" +
                    "<key>hidden</key> <false/>" +
                    "<key>passwordHash</key> <string>PwdHash2</string>" +
                    "</dict>" +
                    "</array>" +

                    "</dict>" +
                    "</dict>" +
                    "<dict>" +
                    "<key>SomeKey1</key> <string>SomeStringValue1</string>" +
                    "</dict>" ;*/

            final String plistWrapper = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n" +
                    "<plist version=\"1.0\">" +
                    "%s" +
                    "</plist>";

            XMLPropertyListConfigurationExt plistWhole = new XMLPropertyListConfigurationExt();
            plistWhole.read(new StringReader(StringEscapeUtils.unescapeJava(encryptedPList)));

            List<XMLPropertyListConfigurationExt> plistList = new ArrayList<>();
            int top = 0;
            int length = inputPlist.length();
            StringBuffer sbParser = null;
            for (int charIndex = 0 ; charIndex < length; charIndex++) {
                char currentChar = inputPlist.charAt(charIndex);
                if(currentChar == '<') {
                    if (inputPlist.substring(charIndex, charIndex + "<dict>".length()).equals("<dict>")) {
                        if (top == 0) {
                            sbParser = new StringBuffer();
                            sbParser.append(currentChar);
                        } else if (top > 0) {
                            sbParser.append(currentChar);
                        }
                        top++;
                    } else if (inputPlist.substring(charIndex, charIndex + "</dict>".length()).equals("</dict>")) {
                        if (top > 1) {
                            sbParser.append(currentChar);
                        } else if (top == 1) {
                            sbParser.append("</dict>");
                            XMLPropertyListConfigurationExt plistDict = new XMLPropertyListConfigurationExt();
                            plistDict.read(new StringReader(String.format(plistWrapper, sbParser.toString())));
                            plistList.add(plistDict);
                            System.out.println("Added dictionary ----> " + sbParser.toString());
                            sbParser = null;
                        }
                        top--;
                    } else {
                        if (top > 0) {
                            sbParser.append(currentChar);
                        }
                    }

                } else {
                    if (top > 0) {
                        sbParser.append(currentChar);
                    }
                }
            }

            /*while ((index < 5) &&(null != subString)) {
                String mainSubString = subString;
                try {
                    subString = mainSubString.substring(mainSubString.indexOf("<dict>"), mainSubString.indexOf("</dict>") + "</dict>".length());
                    System.out.println("Current subString ---> " + subString);
                    XMLPropertyListConfiguration subPlist = new XMLPropertyListConfiguration();
                    subPlist.load(new StringReader(String.format(plistWrapper, subString)));
                    plistList.add(subPlist);
                    subString = mainSubString.replace(subString, "");
                } catch (IndexOutOfBoundsException ex) {
                    break;
                }
                index++;
            }*/

            //xmlPropertyListConfiguration.load(new StringReader(inputPlist));
            /*final SubnodeConfiguration commandNode = xmlPropertyListConfiguration.configurationAt("Command");
            final boolean skipPrimarySetupAccountCreation = commandNode.getBoolean("SkipPrimarySetupAccountCreation");
            final boolean setPrimarySetupAccountAsRegularUser = commandNode.getBoolean("SetPrimarySetupAccountAsRegularUser");
            System.out.println("skipPrimarySetupAccountCreation " + skipPrimarySetupAccountCreation);
            System.out.println("setPrimarySetupAccountAsRegularUser " + setPrimarySetupAccountAsRegularUser);
            //System.out.println("Attributes --> " + Base64.getDecoder().decode((byte[])xmlPropertyListConfiguration.getProperty("Command.myData")));

            System.out.println("myData ---> " + commandNode.getExpressionEngine().query(commandNode.getRootNode(), "myData").get(0));

            final List<XMLPropertyListConfiguration> accountsDataList = commandNode.getList("AutoSetupAdminAccounts");
            for (XMLPropertyListConfiguration subnodeConfiguration : accountsDataList) {
                final String shortName = subnodeConfiguration.getString("shortName");
                System.out.println("shortName " + shortName);
                final String fullName = subnodeConfiguration.getString("fullName");
                System.out.println("fullName " + fullName);
                final boolean hidden = subnodeConfiguration.getBoolean("hidden");
                System.out.println("hidden " + hidden);
                final String passwordHash = (String) subnodeConfiguration.configurationAt("passwordHash").getRootNode().getValue();
                System.out.println("passwordHash " + passwordHash);
            }*/
            String rootElementName = plistWhole.getRootElementName();
            //List<XMLPropertyListConfigurationExt> dictList = plistWhole.childConfigurationsAt(rootElementName);


            for (XMLPropertyListConfigurationExt currentPList : plistList) {
                currentPList.addProperty("date", new Date());
                System.out.println("Date --> " + currentPList.getProperty("date"));
                List<XMLPropertyListConfigurationExt> nestedArray = new ArrayList<>();
                final XMLPropertyListConfigurationExt innerDict1 = new XMLPropertyListConfigurationExt();
                innerDict1.addProperty("innerKey1", "innerValue1");
                nestedArray.add(innerDict1);
                final XMLPropertyListConfigurationExt innerDict2 = new XMLPropertyListConfigurationExt();
                innerDict2.addProperty("innerKey2", "innerValue2");
                nestedArray.add(innerDict2);
                currentPList.addProperty("NestedArray", Arrays.asList(innerDict1, innerDict2));
            }
            File file = File.createTempFile("temp_plist_" + random.nextInt(), ".plist");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            XMLPropertyListConfigurationExt wholePList = new XMLPropertyListConfigurationExt();
            wholePList.read(new StringReader(inputPlist));
            FileHandler fileHandler = new FileHandler(wholePList);
            fileHandler.save(writer);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            final StringBuffer sb = new StringBuffer();
            String line = null;
            while (null != (line = bufferedReader.readLine())) {
                sb.append(line);
            }
            file.delete();
            System.out.println("Without replacement --> " + sb.toString());
            sb.replace(sb.indexOf("<!DOCTYPE plist SYSTEM"), sb.indexOf("<plist version=\"1.0\">"), "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">");
            Properties command = plistWhole.getProperties("Command");
            Iterator<Object> propsIter = command.keySet().iterator();
            while (propsIter.hasNext()) {
                Object key = propsIter.next();
                System.out.println("Key ---> " + key);
                System.out.println("Value ---> " + command.get(key));
            }
            //System.out.println("Payload ---> " + payloadVal);
            CMSSignedData signedData = new CMSSignedData(plistWhole.getProperties("Command").getProperty("Payload").getBytes());
            getData(plistWhole, "Payload");
            System.out.println("PLIST --> " + sb.toString());


            /*XMLPropertyListConfigurationExt secondDict = plistList.get(1);
            Map subNode = secondDict.get(Map.class, "NestedDictionary");
            Object strList = subNode.get("Identifiers");
            if (strList instanceof Map) {
                List strListAsList = (List) strList;
                for (Object str : strListAsList) {
                    System.out.println("Item --> " + str);
                }
            }*/


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void getData(final XMLPropertyListConfigurationExt plist, final String key) throws IOException, ConfigurationException, CMSException {
        File file = File.createTempFile("temp_plist_" + random.nextInt(), ".plist");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        FileHandler fileHandler = new FileHandler(plist);
        fileHandler.save(writer);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        final StringBuffer sb = new StringBuffer();
        String line = null;
        while (null != (line = bufferedReader.readLine())) {
            sb.append(line);
        }
        file.delete();
        getData(sb.toString(), key);

    }

    private static void getData(final String rootDictionary, final String key) throws CMSException {

        System.out.println("Full Plist ---> " + rootDictionary);

        final int startIndex = rootDictionary.indexOf("<key>" + key + "</key>");
        int endIndex = rootDictionary.indexOf("</data>");
        final int endDataTagLength = "</data>".length();
        String currentSubstring = rootDictionary;
        while (endIndex < startIndex) {
            endIndex += endDataTagLength;
            currentSubstring = currentSubstring.substring(endIndex);
            endIndex += currentSubstring.indexOf("</data>");
            currentSubstring = currentSubstring.substring(endIndex);
        }
        System.out.println("Final startIndex ---> " + startIndex);
        System.out.println("Final endIndex ---> " + endIndex);
        System.out.println("Final start substring ---> " + rootDictionary.substring(startIndex));
        System.out.println("Final end substring ---> " + rootDictionary.substring(endIndex));

        final String dataKvpSubString = rootDictionary.substring(startIndex, endIndex);
        System.out.println("Data --> " + dataKvpSubString);
        String dataValueSubstring = dataKvpSubString.substring(dataKvpSubString.indexOf("<data>"));
        String result = dataValueSubstring.substring(dataValueSubstring.indexOf(">") + 1);
        System.out.println("Value --> " + result);

        CMSSignedData signedData = new CMSSignedData(result.getBytes());

    }

}
