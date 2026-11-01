package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.ironsource.sdk.constants.Constants;
import com.my.target.ab;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class MRAIDAdSDKBridge implements AdSDKBridge {
    private static final int CLOSE_BUTTON_SIZE = 50;
    private static final String CONTENT_DESCRIPTION_RESIZED_VIEW = "resizedView";
    private static final String ERROR_EVENT_FORMAT = "mraidBridge.error('%s', '%s');";
    private static final String JAVASCRIPT = "(function (window, console) {\n    var is_array = function (obj) {\n        return Object.prototype.toString.call(obj) === '[object Array]';\n    },\n    registerViewabilityInterest = function(){\n       mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"RegisterViewabilityInterest\", null);\n    },\n    deregisterViewabilityInterest = function(){\n       mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"DeregisterViewabilityInterest\", null);\n    },\n    forEach = function (array, fn) {\n        var i;\n        for (i = 0; i < array.length; i++) {\n            if (i in array) {\n                fn.call(null, array[i], i);\n            }\n        }\n    },\n    events = {\n            error: 'error',\n            ready: 'ready',\n            sizeChange: 'sizeChange',\n            stateChange: 'stateChange',\n            viewableChange: 'viewableChange'\n    },\n    states = [\"loading\",\"default\",\"expanded\",\"resized\",\"hidden\"],\n    placementTypes = [\"inline\", \"interstitial\"],\n    listeners = [],\n    version = '2.0',\n    currentState = \"loading\",\n    currentlyViewable = false,\n    supportedFeatures = null,\n    orientationProperties = {\"allowOrientationChange\":true,\"forceOrientation\":\"none\"},\n    // Error Event fires listeners\n    invokeListeners = function(event, args) {\n        var eventListeners = listeners[event] || [];\n        // fire all the listeners\n        forEach(eventListeners, function(listener){\n            try {\n                listener.apply(null, args);\n            }catch(e){\n                debug(\"Error executing \" + event + \" listener\");\n                debug(e);\n            }\n        });\n    },\n    debug = function(msg) {\n        console.log(\"MRAID log: \" + msg);\n    },\n    readyEvent = function() {\n        debug(\"MRAID ready\");\n        invokeListeners(\"ready\");\n    },\n    errorEvent = function(message, action) {\n        debug(\"error: \" + message + \" action: \" + action);\n        var args = [message, action];\n        invokeListeners(\"error\", args);\n    },\n    stateChangeEvent = function(state) {\n        debug(\"stateChange: \" + state);\n        var args = [state];\n        currentState = state;\n        invokeListeners(\"stateChange\", args);\n    },\n    viewableChangeEvent = function(viewable) {\n        if (viewable != currentlyViewable) {            debug(\"viewableChange: \" + viewable);\n            var args = [viewable];\n            invokeListeners(\"viewableChange\", args);\n            currentlyViewable = viewable;\n        }\n    }, \n    sizeChangeEvent = function(width, height) {\n        debug(\"sizeChange: \" + width + \"x\" + height);\n        var args = [width, height];\n        invokeListeners(\"sizeChange\", args);\n    };\n    window.mraidBridge = {\n            error : errorEvent,\n            ready : readyEvent,\n            stateChange : stateChangeEvent,\n            sizeChange : sizeChangeEvent,\n            viewableChange : viewableChangeEvent\n    };\n    // Define the mraid object\n    window.mraid = {\n            // Command Flow\n            addEventListener : function(event, listener){\n                var eventListeners = listeners[event] || [],\n                alreadyRegistered = false;\n                \n                //verify the event is one that will actually occur\n                if (!events.hasOwnProperty(event)){\n                    return;\n                }\n                \n                //register first set of listeners for this event\n                if (!is_array(listeners[event])) {\n                    listeners[event] = eventListeners;\n                }\n                \n                forEach(eventListeners, function(l){ \n                    // Listener already registered, so no need to add it.\n                        if (listener === l){\n                            alreadyRegistered = true;\n                        }\n                    }\n                );\n                if (!alreadyRegistered){\n                    debug('Registering Listener for ' + event + ': ' + listener)\n                    listeners[event].push(listener);\n                    if (event = 'viewableChange'){ \n                       registerViewabilityInterest();  \n                    } \n                }\n            },\n            removeEventListener : function(event, listener){\n                if (listeners.hasOwnProperty(event)) {\n                    var eventListeners = listeners[event];\n                    if (eventListeners) {\n                        var idx = eventListeners.indexOf(listener);\n                        if (idx !== -1) {\n                           eventListeners.splice(idx, 1);\n                           if (event = 'viewableChange'){ \n                               deregisterViewabilityInterest();  \n                           } \n                        }\n                    }\n                }\n            },\n            useCustomClose: function(bool){\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"UseCustomClose\", JSON.stringify({useCustomClose: bool}));\n            },\n            // Support\n            supports: function(feature){\n                if (!supportedFeatures)\n                {\n                    supportedFeatures = JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"Supports\", null));\n                }\n                return supportedFeatures[feature];\n            },\n            // Properties\n            getVersion: function(){\n                return version;\n            },\n            getState: function(){\n                return currentState;\n            },\n            getPlacementType: function(){\n                var json = JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetPlacementType\", null));\n                return json.placementType;\n            },\n            isViewable: function(){\n                var json = JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"IsViewable\", null));\n                return json.isViewable;\n            },\n            getExpandProperties: function(){\n                return JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetExpandProperties\", null));\n            },\n            setExpandProperties: function(properties){\n                //Backwards compatibility with MRAID 1.0 creatives\n                if (!!properties.lockOrientation){\n                    mraid.setOrientationProperties({\"allowOrientationChange\":false});\n                }\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"SetExpandProperties\", JSON.stringify(properties));\n            },\n            getOrientationProperties: function(){\n                return orientationProperties;\n            },\n            setOrientationProperties: function(properties){\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"SetOrientationProperties\", JSON.stringify(properties));\n            },\n            getResizeProperties: function(){\n                return JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetResizeProperties\", null));\n            },\n            setResizeProperties: function(properties){\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"SetResizeProperties\", JSON.stringify(properties));\n            },\n            getCurrentPosition: function(){\n                return JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetCurrentPosition\", null));\n            },\n            getMaxSize: function(){\n                return JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetMaxSize\", null));\n            },\n            getDefaultPosition: function(){\n                return JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetDefaultPosition\", null));\n            },\n            getScreenSize: function(){\n                return JSON.parse(mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"GetScreenSize\", null));\n            },\n            // Operations\n            open: function(url) {\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"Open\", JSON.stringify({url: url}));\n            },\n            close: function() {\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"Close\", null);\n            },\n            expand: function(url) {\n                if (url !== undefined) {\n                    mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"Expand\", JSON.stringify({url: url}));\n                } else {\n                    mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"Expand\", JSON.stringify({url: \"\"}));\n                }\n            },\n            resize: function() {\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"Resize\", null);\n            },\n            createCalendarEvent: function(eventObject) {\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"CreateCalendarEvent\", JSON.stringify(eventObject));\n            },\n            playVideo: function(url){\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"PlayVideo\", JSON.stringify({url: url}));\n            },\n            storePicture: function(url){\n                mraidObject." + JavascriptInteractor.getExecutorMethodName() + "(\"StorePicture\", JSON.stringify({url: url}));\n            }\n    };\n})(window, console);\n";
    private static final String LOGTAG = "MRAIDAdSDKBridge";
    private static final String MRAID_BRIDGE_NAME = "mraidObject";
    private static final String PLACEMENT_TYPE_INLINE = "inline";
    private static final String PLACEMENT_TYPE_INTERSTITIAL = "interstitial";
    private final AdControlAccessor adControlAccessor;
    private final AdUtils2 adUtils;
    private final AlertDialogFactory alertDialogFactory;
    private final AndroidBuildInfo buildInfo;
    private final Position defaultPosition;
    private final ExpandProperties expandProperties;
    private boolean expandedWithUrl;
    private final GraphicsUtils graphicsUtils;
    private final IntentBuilderFactory intentBuilderFactory;
    private final JavascriptInteractor javascriptInteractor;
    private final LayoutFactory layoutFactory;
    private final MobileAdsLogger logger;
    private final OrientationProperties orientationProperties;
    private final PermissionChecker permissionChecker;
    private final ResizeProperties resizeProperties;
    private ViewGroup resizedView;
    private FrameLayout rootView;
    private SDKEventListener sdkEventListener;
    private final ThreadUtils.ThreadRunner threadRunner;
    private final ViewUtils viewUtils;
    private final WebRequest.WebRequestFactory webRequestFactory;
    private final WebUtils2 webUtils;

    @Override // com.amazon.device.ads.AdSDKBridge
    public String getName() {
        return MRAID_BRIDGE_NAME;
    }

    @Override // com.amazon.device.ads.AdSDKBridge
    public boolean hasNativeExecution() {
        return true;
    }

    MRAIDAdSDKBridge(AdControlAccessor adControlAccessor, JavascriptInteractor javascriptInteractor) {
        this(adControlAccessor, javascriptInteractor, new PermissionChecker(), new MobileAdsLoggerFactory(), new WebRequest.WebRequestFactory(), ThreadUtils.getThreadRunner(), new GraphicsUtils(), new AlertDialogFactory(), new WebUtils2(), new AdUtils2(), new IntentBuilderFactory(), new ExpandProperties(), new OrientationProperties(), new Position(), new ResizeProperties(), new AndroidBuildInfo(), new LayoutFactory(), new ViewUtils());
    }

    MRAIDAdSDKBridge(AdControlAccessor adControlAccessor, JavascriptInteractor javascriptInteractor, PermissionChecker permissionChecker, MobileAdsLoggerFactory mobileAdsLoggerFactory, WebRequest.WebRequestFactory webRequestFactory, ThreadUtils.ThreadRunner threadRunner, GraphicsUtils graphicsUtils, AlertDialogFactory alertDialogFactory, WebUtils2 webUtils2, AdUtils2 adUtils2, IntentBuilderFactory intentBuilderFactory, ExpandProperties expandProperties, OrientationProperties orientationProperties, Position position, ResizeProperties resizeProperties, AndroidBuildInfo androidBuildInfo, LayoutFactory layoutFactory, ViewUtils viewUtils) {
        this.expandedWithUrl = true;
        this.adControlAccessor = adControlAccessor;
        this.javascriptInteractor = javascriptInteractor;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.permissionChecker = permissionChecker;
        this.webRequestFactory = webRequestFactory;
        this.threadRunner = threadRunner;
        this.graphicsUtils = graphicsUtils;
        this.alertDialogFactory = alertDialogFactory;
        this.webUtils = webUtils2;
        this.adUtils = adUtils2;
        this.intentBuilderFactory = intentBuilderFactory;
        this.expandProperties = expandProperties;
        this.orientationProperties = orientationProperties;
        this.defaultPosition = position;
        this.resizeProperties = resizeProperties;
        this.buildInfo = androidBuildInfo;
        this.layoutFactory = layoutFactory;
        this.viewUtils = viewUtils;
        populateJavascriptExecutorsInInteractor();
    }

    private void populateJavascriptExecutorsInInteractor() {
        this.javascriptInteractor.addMethodExecutor(new CloseJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new CreateCalendarEventJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new ExpandJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetCurrentPositionJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetDefaultPositionJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetExpandPropertiesJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetMaxSizeJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetPlacementTypeJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetResizePropertiesJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new GetScreenSizeJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new OpenJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new PlayVideoJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new ResizeJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new SetExpandPropertiesJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new SetOrientationPropertiesJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new SetResizePropertiesJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new StorePictureJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new SupportsJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new UseCustomCloseJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new IsViewableJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new RegisterViewabilityInterestJSIF(this));
        this.javascriptInteractor.addMethodExecutor(new DeregisterViewabilityInterestJSIF(this));
    }

    @Override // com.amazon.device.ads.AdSDKBridge
    public JavascriptInteractor.Executor getJavascriptInteractorExecutor() {
        return this.javascriptInteractor.getExecutor();
    }

    @Override // com.amazon.device.ads.AdSDKBridge
    public String getJavascript() {
        return JAVASCRIPT;
    }

    @Override // com.amazon.device.ads.AdSDKBridge
    public SDKEventListener getSDKEventListener() {
        if (this.sdkEventListener == null) {
            this.sdkEventListener = new MRAIDAdSDKEventListener(this);
        }
        return this.sdkEventListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context getContext() {
        return this.adControlAccessor.getContext();
    }

    void updateDefaultPosition(int i, int i2, int i3, int i4) {
        this.defaultPosition.setSize(new Size(i, i2));
        this.defaultPosition.setX(i3);
        this.defaultPosition.setY(i4);
    }

    public JSONObject getCurrentPosition() {
        if (this.adControlAccessor.getCurrentPosition() == null) {
            fireErrorEvent("Current position is unavailable because the ad has not yet been displayed.", "getCurrentPosition");
            return new Position(new Size(0, 0), 0, 0).toJSONObject();
        }
        return this.adControlAccessor.getCurrentPosition().toJSONObject();
    }

    public JSONObject getDefaultPosition() {
        return this.defaultPosition.toJSONObject();
    }

    public JSONObject getMaxSize() {
        Size maxSize = this.adControlAccessor.getMaxSize();
        if (maxSize == null) {
            return new Size(0, 0).toJSONObject();
        }
        return maxSize.toJSONObject();
    }

    public JSONObject getScreenSize() {
        Size screenSize = this.adControlAccessor.getScreenSize();
        if (screenSize == null) {
            return new Size(0, 0).toJSONObject();
        }
        return screenSize.toJSONObject();
    }

    public String getPlacementType() {
        return this.adControlAccessor.isInterstitial() ? "interstitial" : "inline";
    }

    public String getOrientationProperties() {
        return this.orientationProperties.toString();
    }

    public void setOrientationProperties(JSONObject jSONObject) {
        if (this.adControlAccessor.isInterstitial() && !this.adControlAccessor.isModal()) {
            this.adControlAccessor.orientationChangeAttemptedWhenNotAllowed();
        }
        this.orientationProperties.fromJSONObject(jSONObject);
        orientationPropertyChange();
    }

    public JSONObject getExpandPropertiesForCreative() {
        Size screenSize;
        ExpandProperties clone = this.expandProperties.toClone();
        if (clone.getWidth() == -1) {
            screenSize = this.adControlAccessor.getScreenSize();
            clone.setWidth(screenSize.getWidth());
        } else {
            screenSize = null;
        }
        if (clone.getHeight() == -1) {
            if (screenSize == null) {
                screenSize = this.adControlAccessor.getScreenSize();
            }
            clone.setHeight(screenSize.getHeight());
        }
        return clone.toJSONObject();
    }

    public void setExpandProperties(JSONObject jSONObject) {
        this.expandProperties.fromJSONObject(jSONObject);
        showNativeCloseButtonIfNeeded();
    }

    public JSONObject getResizeProperties() {
        return this.resizeProperties.toJSONObject();
    }

    public void setResizeProperties(JSONObject jSONObject) {
        if (!this.resizeProperties.fromJSONObject(jSONObject)) {
            fireErrorEvent("Invalid resize properties", ab.bB);
            return;
        }
        if (this.resizeProperties.getWidth() < 50 || this.resizeProperties.getHeight() < 50) {
            fireErrorEvent("Resize properties width and height must be greater than 50dp in order to fit the close button.", ab.bB);
            this.resizeProperties.reset();
            return;
        }
        Size maxSize = this.adControlAccessor.getMaxSize();
        if (this.resizeProperties.getWidth() > maxSize.getWidth() || this.resizeProperties.getHeight() > maxSize.getHeight()) {
            fireErrorEvent("Resize properties width and height cannot be larger than the maximum size.", ab.bB);
            this.resizeProperties.reset();
        } else if (this.resizeProperties.getAllowOffscreen()) {
            Size sizeComputeResizeSizeInPixels = computeResizeSizeInPixels(this.resizeProperties);
            int iDeviceIndependentPixelToPixel = this.adUtils.deviceIndependentPixelToPixel(this.defaultPosition.getX() + this.resizeProperties.getOffsetX());
            if (isValidClosePosition(RelativePosition.fromString(this.resizeProperties.getCustomClosePosition()), this.adUtils.deviceIndependentPixelToPixel(this.defaultPosition.getY() + this.resizeProperties.getOffsetY()), iDeviceIndependentPixelToPixel, sizeComputeResizeSizeInPixels, this.adUtils.deviceIndependentPixelToPixel(maxSize.getWidth()), this.adUtils.deviceIndependentPixelToPixel(maxSize.getHeight()))) {
                return;
            }
            fireErrorEvent("Invalid resize properties. Close event area must be entirely on screen.", ab.bB);
            this.resizeProperties.reset();
        }
    }

    public void setUseCustomClose(boolean z) {
        this.expandProperties.setUseCustomClose(Boolean.valueOf(z));
        showNativeCloseButtonIfNeeded();
    }

    private void showNativeCloseButtonIfNeeded() {
        if (this.adControlAccessor.isModal()) {
            this.adControlAccessor.showNativeCloseButtonImage(!this.expandProperties.getUseCustomClose().booleanValue());
        }
    }

    public void close() {
        if (this.adControlAccessor.closeAd()) {
            return;
        }
        fireErrorEvent("Unable to close ad in its current state.", "close");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AdControlAccessor getAdControlAccessor() {
        return this.adControlAccessor;
    }

    public void expand(String str) {
        if (this.adControlAccessor.isInterstitial()) {
            fireErrorEvent("Unable to expand an interstitial ad placement", "expand");
            return;
        }
        if (this.adControlAccessor.isModal()) {
            fireErrorEvent("Unable to expand while expanded.", "expand");
            return;
        }
        if (!this.adControlAccessor.isVisible()) {
            fireErrorEvent("Unable to expand ad while it is not visible.", "expand");
            return;
        }
        if ((this.expandProperties.getWidth() < 50 && this.expandProperties.getWidth() != -1) || (this.expandProperties.getHeight() < 50 && this.expandProperties.getHeight() != -1)) {
            fireErrorEvent("Expand size is too small, must leave room for close.", "expand");
            return;
        }
        if (!StringUtils.isNullOrWhiteSpace(str)) {
            if (this.webUtils.isUrlValid(str)) {
                final ExpandProperties clone = this.expandProperties.toClone();
                this.adControlAccessor.preloadUrl(str, new PreloadCallback() { // from class: com.amazon.device.ads.MRAIDAdSDKBridge.1
                    @Override // com.amazon.device.ads.PreloadCallback
                    public void onPreloadComplete(String str2) {
                        MRAIDAdSDKBridge.this.getAdControlAccessor().injectJavascriptPreload("mraidBridge.stateChange('expanded');");
                        MRAIDAdSDKBridge.this.getAdControlAccessor().injectJavascriptPreload("mraidBridge.ready();");
                        AdControllerFactory.cacheAdControlAccessor(MRAIDAdSDKBridge.this.getAdControlAccessor());
                        MRAIDAdSDKBridge.this.launchExpandActivity(str2, clone);
                    }
                });
                return;
            } else {
                fireErrorEvent("Unable to expand with invalid URL.", "expand");
                return;
            }
        }
        AdControllerFactory.cacheAdControlAccessor(this.adControlAccessor);
        launchExpandActivity(null, this.expandProperties);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchExpandActivity(String str, ExpandProperties expandProperties) {
        if (this.intentBuilderFactory.createIntentBuilder().withClass(AdActivity.class).withContext(this.adControlAccessor.getContext().getApplicationContext()).withExtra("adapter", ModalAdActivityAdapter.class.getName()).withExtra("url", str).withExtra("expandProperties", expandProperties.toString()).withExtra("orientationProperties", this.orientationProperties.toString()).fireIntent()) {
            this.logger.d("Successfully expanded ad");
        }
    }

    public void resize() {
        if (this.adControlAccessor.isInterstitial()) {
            fireErrorEvent("Unable to resize an interstitial ad placement.", ab.bA);
            return;
        }
        if (this.adControlAccessor.isModal()) {
            fireErrorEvent("Unable to resize while expanded.", ab.bA);
            return;
        }
        if (!this.adControlAccessor.isVisible()) {
            fireErrorEvent("Unable to resize ad while it is not visible.", ab.bA);
            return;
        }
        ResizeProperties resizeProperties = this.resizeProperties;
        if (resizeProperties == null || !resizeProperties.areResizePropertiesSet()) {
            fireErrorEvent("Resize properties must be set before calling resize.", ab.bA);
        } else {
            resizeAd(this.resizeProperties);
        }
    }

    public void playVideo(String str) {
        if (!this.adControlAccessor.isVisible()) {
            fireErrorEvent("Unable to play a video while the ad is not visible", ab.bE);
            return;
        }
        if (StringUtils.isNullOrEmpty(str)) {
            fireErrorEvent("Unable to play a video without a URL", ab.bE);
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            Intent intent = new Intent(getContext(), (Class<?>) AdActivity.class);
            intent.putExtra("adapter", VideoActionHandler.class.getName());
            intent.putExtras(bundle);
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            this.logger.d("Failed to open VideoAction activity");
            fireErrorEvent("Internal SDK Failure. Unable to launch VideoActionHandler", ab.bE);
        }
    }

    public void open(String str) {
        if (!this.adControlAccessor.isVisible()) {
            fireErrorEvent("Unable to open a URL while the ad is not visible", ab.bz);
            return;
        }
        this.logger.d("Opening URL " + str);
        if (this.webUtils.isUrlValid(str)) {
            String scheme = WebUtils.getScheme(str);
            if ("http".equals(scheme) || "https".equals(scheme)) {
                new InAppBrowser.InAppBrowserBuilder().withContext(getContext()).withExternalBrowserButton().withUrl(str).show();
                return;
            } else {
                this.adControlAccessor.loadUrl(str);
                return;
            }
        }
        String str2 = "URL " + str + " is not a valid URL";
        this.logger.d(str2);
        fireErrorEvent(str2, ab.bz);
    }

    public JSONObject getSupportedFeatures() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AdWebViewClient.SMS, getContext().getPackageManager().hasSystemFeature("android.hardware.telephony"));
            jSONObject.put(AdWebViewClient.TELEPHONE, getContext().getPackageManager().hasSystemFeature("android.hardware.telephony"));
            jSONObject.put("calendar", AndroidTargetUtils.isAtLeastAndroidAPI(14));
            jSONObject.put(ab.bF, this.permissionChecker.hasWriteExternalStoragePermission(getContext()));
            jSONObject.put("inlineVideo", AndroidTargetUtils.isAtLeastAndroidAPI(11));
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void createCalendarEvent(String str, String str2, String str3, String str4, String str5) {
        if (!AndroidTargetUtils.isAtLeastAndroidAPI(14)) {
            this.logger.d("API version does not support calendar operations.");
            fireErrorEvent("API version does not support calendar operations.", ab.bG);
            return;
        }
        try {
            createCalendarIntent(new CalendarEventParameters(str, str2, str3, str4, str5));
        } catch (IllegalArgumentException e) {
            this.logger.d(e.getMessage());
            fireErrorEvent(e.getMessage(), ab.bG);
        }
    }

    @TargetApi(14)
    private void createCalendarIntent(CalendarEventParameters calendarEventParameters) {
        Intent type = new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.item/event");
        type.putExtra("title", calendarEventParameters.getDescription());
        if (!StringUtils.isNullOrEmpty(calendarEventParameters.getLocation())) {
            type.putExtra("eventLocation", calendarEventParameters.getLocation());
        }
        if (!StringUtils.isNullOrEmpty(calendarEventParameters.getSummary())) {
            type.putExtra("description", calendarEventParameters.getSummary());
        }
        type.putExtra("beginTime", calendarEventParameters.getStart().getTime());
        if (calendarEventParameters.getEnd() != null) {
            type.putExtra("endTime", calendarEventParameters.getEnd().getTime());
        }
        getContext().startActivity(type);
    }

    public void storePicture(final String str) {
        if (!this.permissionChecker.hasWriteExternalStoragePermission(getContext())) {
            fireErrorEvent("Picture could not be stored because permission was denied.", ab.bF);
        } else {
            this.threadRunner.execute(new Runnable() { // from class: com.amazon.device.ads.MRAIDAdSDKBridge.2
                @Override // java.lang.Runnable
                public void run() {
                    MRAIDAdSDKBridge.this.fetchPicture(str);
                }
            }, ThreadUtils.ExecutionStyle.RUN_ASAP, ThreadUtils.ExecutionThread.BACKGROUND_THREAD);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchPicture(String str) {
        WebRequest webRequestCreateWebRequest = this.webRequestFactory.createWebRequest();
        webRequestCreateWebRequest.enableLog(true);
        webRequestCreateWebRequest.setUrlString(str);
        try {
            WebRequest.WebResponse webResponseMakeCall = webRequestCreateWebRequest.makeCall();
            if (webResponseMakeCall == null) {
                fireErrorEvent("Server could not be contacted to download picture.", ab.bF);
                return;
            }
            final Bitmap asBitmap = new ImageResponseReader(webResponseMakeCall.getResponseReader(), this.graphicsUtils).readAsBitmap();
            if (asBitmap == null) {
                fireErrorEvent("Picture could not be retrieved from server.", ab.bF);
            } else {
                this.threadRunner.execute(new Runnable() { // from class: com.amazon.device.ads.MRAIDAdSDKBridge.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MRAIDAdSDKBridge.this.savePicture(asBitmap);
                    }
                }, ThreadUtils.ExecutionStyle.SCHEDULE, ThreadUtils.ExecutionThread.MAIN_THREAD);
            }
        } catch (WebRequest.WebRequestException unused) {
            fireErrorEvent("Server could not be contacted to download picture.", ab.bF);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void savePicture(final Bitmap bitmap) {
        AlertDialog.Builder builderCreateBuilder = this.alertDialogFactory.createBuilder(getContext());
        builderCreateBuilder.setTitle("Would you like to save the image to your gallery?");
        builderCreateBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.amazon.device.ads.MRAIDAdSDKBridge.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                String strInsertImageInMediaStore = MRAIDAdSDKBridge.this.graphicsUtils.insertImageInMediaStore(MRAIDAdSDKBridge.this.getContext(), bitmap, "AdImage", "Image created by rich media ad.");
                if (StringUtils.isNullOrEmpty(strInsertImageInMediaStore)) {
                    MRAIDAdSDKBridge.this.fireErrorEvent("Picture could not be stored to device.", ab.bF);
                } else {
                    MediaScannerConnection.scanFile(MRAIDAdSDKBridge.this.getContext(), new String[]{strInsertImageInMediaStore}, null, null);
                }
            }
        });
        builderCreateBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() { // from class: com.amazon.device.ads.MRAIDAdSDKBridge.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MRAIDAdSDKBridge.this.fireErrorEvent("User chose not to store image.", ab.bF);
            }
        });
        builderCreateBuilder.show();
    }

    void collapseExpandedAd(final AdControlAccessor adControlAccessor) {
        this.logger.d("Collapsing expanded ad " + this);
        this.threadRunner.execute(new Runnable() { // from class: com.amazon.device.ads.MRAIDAdSDKBridge.6
            @Override // java.lang.Runnable
            public void run() {
                MRAIDAdSDKBridge.this.collapseExpandedAdOnThread(adControlAccessor);
            }
        }, ThreadUtils.ExecutionStyle.RUN_ASAP, ThreadUtils.ExecutionThread.MAIN_THREAD);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"InlinedApi"})
    public void collapseExpandedAdOnThread(final AdControlAccessor adControlAccessor) {
        adControlAccessor.setAdActivity(null);
        if (this.expandedWithUrl) {
            this.logger.d("Expanded With URL");
            adControlAccessor.popView();
        } else {
            this.logger.d("Not Expanded with URL");
        }
        adControlAccessor.moveViewBackToParent(new FrameLayout.LayoutParams(-1, -1, 17));
        adControlAccessor.removeCloseButton();
        adControlAccessor.fireAdEvent(new AdEvent(AdEvent.AdEventType.CLOSED));
        adControlAccessor.injectJavascript("mraidBridge.stateChange('default');");
        adControlAccessor.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.device.ads.MRAIDAdSDKBridge.7
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                adControlAccessor.removeOnGlobalLayoutListener(this);
                MRAIDAdSDKBridge.this.reportSizeChangeEvent();
            }
        });
    }

    void resizeAd(final ResizeProperties resizeProperties) {
        final Size sizeComputeResizeSizeInPixels = computeResizeSizeInPixels(resizeProperties);
        this.threadRunner.execute(new Runnable() { // from class: com.amazon.device.ads.MRAIDAdSDKBridge.8
            @Override // java.lang.Runnable
            public void run() {
                MRAIDAdSDKBridge.this.resizeAdOnThread(resizeProperties, sizeComputeResizeSizeInPixels);
            }
        }, ThreadUtils.ExecutionStyle.RUN_ASAP, ThreadUtils.ExecutionThread.MAIN_THREAD);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resizeAdOnThread(final ResizeProperties resizeProperties, final Size size) {
        Size maxSize = this.adControlAccessor.getMaxSize();
        if (maxSize == null) {
            this.adControlAccessor.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.device.ads.MRAIDAdSDKBridge.9
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    MRAIDAdSDKBridge.this.adControlAccessor.removeOnGlobalLayoutListener(this);
                    MRAIDAdSDKBridge.this.resizeAdHelper(resizeProperties, size, MRAIDAdSDKBridge.this.adControlAccessor.getMaxSize());
                }
            });
        } else {
            resizeAdHelper(resizeProperties, size, maxSize);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resizeAdHelper(ResizeProperties resizeProperties, Size size, Size size2) {
        if (size2 == null) {
            this.logger.d("Size is null");
            return;
        }
        createResizedView();
        int iDeviceIndependentPixelToPixel = this.adUtils.deviceIndependentPixelToPixel(this.defaultPosition.getX() + resizeProperties.getOffsetX());
        int iDeviceIndependentPixelToPixel2 = this.adUtils.deviceIndependentPixelToPixel(this.defaultPosition.getY() + resizeProperties.getOffsetY());
        RelativePosition relativePositionFromString = RelativePosition.fromString(resizeProperties.getCustomClosePosition());
        int iDeviceIndependentPixelToPixel3 = this.adUtils.deviceIndependentPixelToPixel(size2.getWidth());
        int iDeviceIndependentPixelToPixel4 = this.adUtils.deviceIndependentPixelToPixel(size2.getHeight());
        if (!resizeProperties.getAllowOffscreen()) {
            if (size.getWidth() > iDeviceIndependentPixelToPixel3) {
                size.setWidth(iDeviceIndependentPixelToPixel3);
            }
            if (size.getHeight() > iDeviceIndependentPixelToPixel4) {
                size.setHeight(iDeviceIndependentPixelToPixel4);
            }
            if (iDeviceIndependentPixelToPixel < 0) {
                iDeviceIndependentPixelToPixel = 0;
            } else if (size.getWidth() + iDeviceIndependentPixelToPixel > iDeviceIndependentPixelToPixel3) {
                iDeviceIndependentPixelToPixel = iDeviceIndependentPixelToPixel3 - size.getWidth();
            }
            if (iDeviceIndependentPixelToPixel2 < 0) {
                iDeviceIndependentPixelToPixel2 = 0;
            } else if (size.getHeight() + iDeviceIndependentPixelToPixel2 > iDeviceIndependentPixelToPixel4) {
                iDeviceIndependentPixelToPixel2 = iDeviceIndependentPixelToPixel4 - size.getHeight();
            }
        } else if (!isValidClosePosition(relativePositionFromString, iDeviceIndependentPixelToPixel2, iDeviceIndependentPixelToPixel, size, iDeviceIndependentPixelToPixel3, iDeviceIndependentPixelToPixel4)) {
            fireErrorEvent("Resize failed because close event area must be entirely on screen.", ab.bA);
            return;
        }
        this.adControlAccessor.moveViewToViewGroup(this.resizedView, new RelativeLayout.LayoutParams(size.getWidth(), size.getHeight()), false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(size.getWidth(), size.getHeight());
        layoutParams.gravity = 48;
        layoutParams.leftMargin = iDeviceIndependentPixelToPixel;
        layoutParams.topMargin = iDeviceIndependentPixelToPixel2;
        if (this.rootView.equals(this.resizedView.getParent())) {
            this.resizedView.setLayoutParams(layoutParams);
        } else {
            this.rootView.addView(this.resizedView, layoutParams);
        }
        this.adControlAccessor.enableCloseButton(false, relativePositionFromString);
        final ViewTreeObserver viewTreeObserver = this.resizedView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.device.ads.MRAIDAdSDKBridge.10
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                MRAIDAdSDKBridge.this.viewUtils.removeOnGlobalLayoutListener(viewTreeObserver, this);
                int[] iArr = new int[2];
                MRAIDAdSDKBridge.this.resizedView.getLocationOnScreen(iArr);
                Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + MRAIDAdSDKBridge.this.resizedView.getWidth(), iArr[1] + MRAIDAdSDKBridge.this.resizedView.getHeight());
                AdEvent adEvent = new AdEvent(AdEvent.AdEventType.RESIZED);
                adEvent.setParameter(AdEvent.POSITION_ON_SCREEN, rect);
                MRAIDAdSDKBridge.this.adControlAccessor.fireAdEvent(adEvent);
                MRAIDAdSDKBridge.this.adControlAccessor.injectJavascript("mraidBridge.stateChange('resized');");
                MRAIDAdSDKBridge.this.reportSizeChangeEvent();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isViewable() {
        return this.adControlAccessor.isViewable();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean isValidClosePosition(RelativePosition relativePosition, int i, int i2, Size size, int i3, int i4) {
        int height;
        int i5;
        int i6;
        int iDeviceIndependentPixelToPixel = this.adUtils.deviceIndependentPixelToPixel(50);
        switch (relativePosition) {
            case TOP_LEFT:
                height = i + iDeviceIndependentPixelToPixel;
                i6 = i2 + iDeviceIndependentPixelToPixel;
                break;
            case TOP_RIGHT:
                int width = size.getWidth() + i2;
                i5 = width - iDeviceIndependentPixelToPixel;
                i6 = width;
                height = i + iDeviceIndependentPixelToPixel;
                i2 = i5;
                break;
            case TOP_CENTER:
                i2 = ((size.getWidth() / 2) + i2) - (iDeviceIndependentPixelToPixel / 2);
                height = i + iDeviceIndependentPixelToPixel;
                i6 = i2 + iDeviceIndependentPixelToPixel;
                break;
            case BOTTOM_LEFT:
                height = size.getHeight() + i;
                i = height - iDeviceIndependentPixelToPixel;
                i6 = i2 + iDeviceIndependentPixelToPixel;
                break;
            case BOTTOM_RIGHT:
                height = size.getHeight() + i;
                int width2 = size.getWidth() + i2;
                i5 = width2 - iDeviceIndependentPixelToPixel;
                i6 = width2;
                i = height - iDeviceIndependentPixelToPixel;
                i2 = i5;
                break;
            case BOTTOM_CENTER:
                height = size.getHeight() + i;
                i2 = ((size.getWidth() / 2) + i2) - (iDeviceIndependentPixelToPixel / 2);
                i = height - iDeviceIndependentPixelToPixel;
                i6 = i2 + iDeviceIndependentPixelToPixel;
                break;
            case CENTER:
                int height2 = (size.getHeight() / 2) + i;
                int i7 = iDeviceIndependentPixelToPixel / 2;
                int i8 = height2 - i7;
                i2 = ((size.getWidth() / 2) + i2) - i7;
                i6 = i2 + iDeviceIndependentPixelToPixel;
                i = i8;
                height = i8 + iDeviceIndependentPixelToPixel;
                break;
            default:
                height = 0;
                i = 0;
                i2 = 0;
                i6 = 0;
                break;
        }
        return i >= 0 && i2 >= 0 && height <= i4 && i6 <= i3;
    }

    private Size computeResizeSizeInPixels(ResizeProperties resizeProperties) {
        return new Size(this.adUtils.deviceIndependentPixelToPixel(resizeProperties.getWidth()), this.adUtils.deviceIndependentPixelToPixel(resizeProperties.getHeight()));
    }

    private void createResizedView() {
        if (this.resizedView == null) {
            if (this.rootView == null) {
                this.rootView = (FrameLayout) this.adControlAccessor.getRootView();
            }
            this.resizedView = this.layoutFactory.createLayout(getContext(), LayoutFactory.LayoutType.RELATIVE_LAYOUT, CONTENT_DESCRIPTION_RESIZED_VIEW);
        }
    }

    void reportSizeChangeEvent() {
        Position currentPosition = this.adControlAccessor.getCurrentPosition();
        if (currentPosition != null) {
            this.adControlAccessor.injectJavascript("mraidBridge.sizeChange(" + currentPosition.getSize().getWidth() + "," + currentPosition.getSize().getHeight() + ");");
        }
    }

    void orientationPropertyChange() {
        if (this.adControlAccessor.isVisible() && this.adControlAccessor.isModal()) {
            Activity adActivity = this.adControlAccessor.getAdActivity();
            if (adActivity == null) {
                this.logger.e("unable to handle orientation property change on a non-expanded ad");
                return;
            }
            int requestedOrientation = adActivity.getRequestedOrientation();
            Position currentPosition = this.adControlAccessor.getCurrentPosition();
            this.logger.d("Current Orientation: " + requestedOrientation);
            int i = AnonymousClass12.$SwitchMap$com$amazon$device$ads$ForceOrientation[this.orientationProperties.getForceOrientation().ordinal()];
            if (i == 1) {
                adActivity.setRequestedOrientation(7);
            } else if (i == 2) {
                adActivity.setRequestedOrientation(6);
            }
            if (ForceOrientation.NONE.equals(this.orientationProperties.getForceOrientation())) {
                if (this.orientationProperties.isAllowOrientationChange().booleanValue()) {
                    if (adActivity.getRequestedOrientation() != -1) {
                        adActivity.setRequestedOrientation(-1);
                    }
                } else if (this.adControlAccessor.isModal()) {
                    adActivity.setRequestedOrientation(DisplayUtils.determineCanonicalScreenOrientation(adActivity, this.buildInfo));
                }
            }
            int requestedOrientation2 = adActivity.getRequestedOrientation();
            this.logger.d("New Orientation: " + requestedOrientation2);
            if (requestedOrientation2 == requestedOrientation || currentPosition == null) {
                return;
            }
            if (currentPosition.getSize().getWidth() != this.adControlAccessor.getCurrentPosition().getSize().getWidth()) {
                this.adControlAccessor.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.device.ads.MRAIDAdSDKBridge.11
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        MRAIDAdSDKBridge.this.adControlAccessor.removeOnGlobalLayoutListener(this);
                        MRAIDAdSDKBridge.this.reportSizeChangeEvent();
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.device.ads.MRAIDAdSDKBridge$12, reason: invalid class name */
    static /* synthetic */ class AnonymousClass12 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$ForceOrientation = new int[ForceOrientation.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$ads$ForceOrientation[ForceOrientation.PORTRAIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$ForceOrientation[ForceOrientation.LANDSCAPE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$ForceOrientation[ForceOrientation.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$device$ads$RelativePosition = new int[RelativePosition.values().length];
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.TOP_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.TOP_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.TOP_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.BOTTOM_LEFT.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.BOTTOM_RIGHT.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.BOTTOM_CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$RelativePosition[RelativePosition.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerViewabilityInterest() {
        this.adControlAccessor.registerViewabilityInterest();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deregisterViewabilityInterest() {
        this.adControlAccessor.deregisterViewabilityInterest();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fireErrorEvent(String str, String str2) {
        this.adControlAccessor.injectJavascript(String.format(Locale.US, ERROR_EVENT_FORMAT, str, str2));
    }

    private static class GetCurrentPositionJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "GetCurrentPosition";
        private final MRAIDAdSDKBridge bridge;

        public GetCurrentPositionJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getCurrentPosition();
        }
    }

    private static class GetDefaultPositionJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "GetDefaultPosition";
        private final MRAIDAdSDKBridge bridge;

        public GetDefaultPositionJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getDefaultPosition();
        }
    }

    private static class GetMaxSizeJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "GetMaxSize";
        private final MRAIDAdSDKBridge bridge;

        public GetMaxSizeJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getMaxSize();
        }
    }

    private static class GetScreenSizeJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "GetScreenSize";
        private final MRAIDAdSDKBridge bridge;

        public GetScreenSizeJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getScreenSize();
        }
    }

    private static class GetPlacementTypeJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "GetPlacementType";
        private final MRAIDAdSDKBridge bridge;

        public GetPlacementTypeJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            JSONUtils.put(jSONObject2, "placementType", this.bridge.getPlacementType());
            return jSONObject2;
        }
    }

    private static class SetOrientationPropertiesJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "SetOrientationProperties";
        private final MRAIDAdSDKBridge bridge;

        public SetOrientationPropertiesJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.setOrientationProperties(jSONObject);
            return null;
        }
    }

    private static class GetExpandPropertiesJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "GetExpandProperties";
        private final MRAIDAdSDKBridge bridge;

        public GetExpandPropertiesJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getExpandPropertiesForCreative();
        }
    }

    private static class SetExpandPropertiesJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "SetExpandProperties";
        private final MRAIDAdSDKBridge bridge;

        public SetExpandPropertiesJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.setExpandProperties(jSONObject);
            return null;
        }
    }

    private static class GetResizePropertiesJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "GetResizeProperties";
        private final MRAIDAdSDKBridge bridge;

        public GetResizePropertiesJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getResizeProperties();
        }
    }

    private static class SetResizePropertiesJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "SetResizeProperties";
        private final MRAIDAdSDKBridge bridge;

        public SetResizePropertiesJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.setResizeProperties(jSONObject);
            return null;
        }
    }

    private static class UseCustomCloseJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "UseCustomClose";
        private final MRAIDAdSDKBridge bridge;

        public UseCustomCloseJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.setUseCustomClose(JSONUtils.getBooleanFromJSON(jSONObject, ab.by, false));
            return null;
        }
    }

    private static class IsViewableJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "IsViewable";
        private final MRAIDAdSDKBridge bridge;

        public IsViewableJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            JSONUtils.put(jSONObject2, Constants.ParametersKeys.IS_VIEWABLE, this.bridge.isViewable());
            return jSONObject2;
        }
    }

    private static class CloseJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "Close";
        private final MRAIDAdSDKBridge bridge;

        public CloseJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super("Close");
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.close();
            return null;
        }
    }

    private static class ExpandJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "Expand";
        private final MRAIDAdSDKBridge bridge;

        public ExpandJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.expand(JSONUtils.getStringFromJSON(jSONObject, "url", null));
            return null;
        }
    }

    private static class ResizeJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "Resize";
        private final MRAIDAdSDKBridge bridge;

        public ResizeJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.resize();
            return null;
        }
    }

    private static class PlayVideoJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "PlayVideo";
        private final MRAIDAdSDKBridge bridge;

        public PlayVideoJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.playVideo(JSONUtils.getStringFromJSON(jSONObject, "url", null));
            return null;
        }
    }

    private static class OpenJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "Open";
        private final MRAIDAdSDKBridge bridge;

        public OpenJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.open(JSONUtils.getStringFromJSON(jSONObject, "url", null));
            return null;
        }
    }

    private static class SupportsJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "Supports";
        private final MRAIDAdSDKBridge bridge;

        public SupportsJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            return this.bridge.getSupportedFeatures();
        }
    }

    private static class CreateCalendarEventJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "CreateCalendarEvent";
        private final MRAIDAdSDKBridge bridge;

        public CreateCalendarEventJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.createCalendarEvent(JSONUtils.getStringFromJSON(jSONObject, "description", null), JSONUtils.getStringFromJSON(jSONObject, "location", null), JSONUtils.getStringFromJSON(jSONObject, "summary", null), JSONUtils.getStringFromJSON(jSONObject, "start", null), JSONUtils.getStringFromJSON(jSONObject, TtmlNode.END, null));
            return null;
        }
    }

    private static class StorePictureJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "StorePicture";
        private final MRAIDAdSDKBridge bridge;

        public StorePictureJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        public JSONObject execute(JSONObject jSONObject) {
            this.bridge.storePicture(JSONUtils.getStringFromJSON(jSONObject, "url", null));
            return null;
        }
    }

    private static class RegisterViewabilityInterestJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "RegisterViewabilityInterest";
        private final MRAIDAdSDKBridge bridge;

        public RegisterViewabilityInterestJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        protected JSONObject execute(JSONObject jSONObject) {
            this.bridge.registerViewabilityInterest();
            return null;
        }
    }

    private static class DeregisterViewabilityInterestJSIF extends JavascriptInteractor.JavascriptMethodExecutor {
        private static final String name = "DeregisterViewabilityInterest";
        private final MRAIDAdSDKBridge bridge;

        public DeregisterViewabilityInterestJSIF(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
            super(name);
            this.bridge = mRAIDAdSDKBridge;
        }

        @Override // com.amazon.device.ads.JavascriptInteractor.JavascriptMethodExecutor
        protected JSONObject execute(JSONObject jSONObject) {
            this.bridge.deregisterViewabilityInterest();
            return null;
        }
    }

    static class AlertDialogFactory {
        AlertDialogFactory() {
        }

        public AlertDialog.Builder createBuilder(Context context) {
            return new AlertDialog.Builder(context);
        }
    }
}
