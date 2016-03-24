// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('starter', ['ionic', 'ui.router','ngCordova'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    if(window.cordova && window.cordova.plugins.Keyboard) {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);

      // Don't remove this line unless you know what you are doing. It stops the viewport
      // from snapping when text inputs are focused. Ionic handles this internally for
      // a much nicer keyboard experience.
      cordova.plugins.Keyboard.disableScroll(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
})


.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
  $urlRouterProvider.otherwise('/')

  $stateProvider.state('firebase', {
    url: '/',
    templateUrl: 'views/firebase.html'
  })
  
  .state('home', {
    url: '/home',
    templateUrl: 'views/home.html',
      controller:'getweather'
  })
})

 .controller('getweather', function ($scope, $ionicModal, $location, $state, $http, $cordovaCamera, $ionicHistory) {

    city = document.getElementById('cityname').value;
        $scope.cityname='Name: '+city;
    var weather = { };
    $http.jsonp('http://api.openweathermap.org/data/2.5/weather?q='+city+'&appid=fe8bd79a2a1b8bf2f7abcc7bb4314b91&callback=JSON_CALLBACK').then(function(data) {
                       console.log(data);
        $scope.x=data;
        $scope.temp='Temperature is: '+data.data.main.temp;
        $scope.pressure='Pressure is: '+data.data.main.pressure;
        $scope.humidity='Humidity is: '+data.data.main.humidity;
        $scope.country='Country is: '+data.data.sys.country;
                                                                })
    
    $http.get('http://maps.googleapis.com/maps/api/geocode/json?address='+city).then(function(data2) {
        $scope.formatted_address='The address is: '+data2.data.results[0].formatted_address+' ( from google maps api)';
       })
    $scope.getdata = function () {
      $location.path('/home');
     $state.go('home');  
    };
    
    $scope.docsupload=function(){
        var options = {
      quality: 75,
      destinationType: Camera.DestinationType.DATA_URL,
      sourceType: Camera.PictureSourceType.CAMERA,
      allowEdit: true,
      encodingType: Camera.EncodingType.JPEG,
      targetWidth: 500,
      targetHeight: 500,
      popoverOptions: CameraPopoverOptions,
      saveToPhotoAlbum: false,
	  correctOrientation:true
    };
    
       $cordovaCamera.getPicture(options).then(function(imageData) {
            $scope.driving= "data:image/jpeg;base64," + imageData;
      var image = document.getElementById('myImage');
      image.src = "data:image/jpeg;base64," + imageData;
           alert("document is uploaded");
    }, function(err) {
     
    });
       
    }
    
    
})