/**
 * Created by Promar on 22.11.2016.
 */

app.controller('HistoryCtrl', ['$scope', '$http', '$route', '$rootScope','HOST','ngDialog',
    function ($scope, $http, $route, $rootScope, TraderService,HOST, ngDialog) {

    $scope.deleteDocument = [];



        $http({
            method: 'GET',
            url: '/all_deleteE'
        }).success(function (data) {
            $scope.deleteDocument = data;

        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');

        });





}]);