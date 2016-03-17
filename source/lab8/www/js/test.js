describe('weatherdetails',function(){
    var scope;
    beforeEach(angular.mock.module('starter'));
    beforeEach(angular.mock.inject(function($scope, $ionicModal, $location, $state, $http){
 scope=$rootScope.$new();
        $controller('weatherdetails',{$scope:scope});
        
    }));
    
    it('should have variable text = "yes im working..!!!"', function(){
        expect(scope.text).toBe('yes im working..!!!');
    });
    
});