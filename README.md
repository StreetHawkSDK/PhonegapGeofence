# StreetHawk's Geofence Plugin

## Introduction
The repository hosts Phonegap plugin of StreetHawk SDK's Geofence module. The plugin  is required if you wish to run campaigns based on your application user entering or exiting a geofence.

## Integration Steps
Add Geofence plugin by running the below command.
```
$ cd <APPLICATION_DIRECTORY>
$ cordova plugin add streethawkgeofence
```
Note that Geofence plugin requires StreetHawk Analytics plugin which can be added by running the below mentioned command

```
cordova plugin add streethawkanalytics  --variable APP_KEY=<YOUR_APPLICATIONS_APP_KEY> --variable URL_SCHEME=<URL_SCHEME_OF_APP>
```
Replace YOUR_APPLICATIONS_APP_KEY with app_key registered with StreetHawk for your application and URL_SCHEME_OF_APP with deeplinking scheme of your application.

## Documentation
Click [here](https://streethawk.freshdesk.com/solution/articles/5000680136) for detailed documentation of StreetHawk's Analytics plugin

## Other StreetHawk Plugins

* [Growth](https://github.com/StreetHawkSDK/PhonegapGrowth) for Viral and organic growth
* [Push](https://github.com/StreetHawkSDK/PhonegapPush) Push messaging in your application
* [Beacon](https://github.com/StreetHawkSDK/PhonegapBeacon) for running beacon based campaigns
* [Location](https://github.com/StreetHawkSDK/PhonegapLocations) for running campaigns based on user's location
* [Feeds](https://github.com/StreetHawkSDK/PhonegapFeeds) for sending feeds in your application

[Streethawk](http://www.streethawk.com) 
