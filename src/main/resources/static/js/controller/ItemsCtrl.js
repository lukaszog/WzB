/**
 * Created by Promar on 26.11.2016.
 */
app.controller('ItemsOperation', ['$scope', '$http', '$window', '$route', 'documentWZ', 'HOST', function ($scope, $http, $window, $route, documentWZ, HOST) {

    $scope.form = {};
    $scope.listItems = '';
    $scope.listClient = '';
    $scope.resultListClient = [];
    $scope.resultListTrader = [];


    $scope.reloadRoute = function () {
        $route.reload();
    };



        $http({
            method: 'GET',
            url: HOST + '/findAll_items',
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $scope.listItems = data;

            }).error(function (data) {
            $scope.listClient = 'Nie udało się pobrać listy handlowców.'
        });





    $scope.addItems = function () {
        $http({
            method: 'POST',
            url: HOST + '/save_items',
            data: {
                "numberPro": $scope.form.numberPro,
                "contentItem": $scope.form.contentItem,
                "clientName": $scope.form.clientName,
                "traderName": $scope.form.traderName,
                "nameTeam": $scope.form.nameTeam,
                "kbn": $scope.form.kbn,
                "provider": $scope.form.provider,
                "businessSector": $scope.form.businessSector,
                "dateAccepted": $scope.form.date,
                "priceItem": $scope.form.priceItem
            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                console.log('dodano');
                }
            ).error(function (data) {
                console.log('nie dodano');
        });
    };

}]);