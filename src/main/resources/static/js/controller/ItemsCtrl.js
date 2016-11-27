/**
 * Created by Promar on 26.11.2016.
 */
app.controller('ItemsOperation', ['$scope', '$rootScope', '$http', '$window', '$route', '$timeout', 'ItemsService', 'HOST', function ($scope, $rootScope, $http, $window, $route, $timeout, ItemsService, HOST) {

    $scope.form = {};
    $scope.listItems = '';
    $rootScope.listClient = '';
    $scope.resultListClient = [];
    $scope.resultListTrader = [];
    $scope.load = true;
    $scope.filterNameClient = false;
    $scope.filterNameTrader = false;
    $scope.filterNameTeam = false;
    $scope.filterProvider = false;
    $scope.filterDate = false;
    $scope.filterNumberPro = false;
    $scope.filterKBN = false;
    $scope.filterBusinessSector = false;

    $scope.showNumber = function() {
        $scope.filterNumberPro = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showClient = function() {
        $scope.filterNameClient = true;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterNumberPro = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showTrader = function() {
        $scope.filterNameTrader = true;
        $scope.filterNameClient = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterNumberPro = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showTeam = function() {
        $scope.filterNameTeam = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterNumberPro = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showProvider = function() {
        $scope.filterProvider = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterDate = false;
        $scope.filterNumberPro = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showDate = function() {
        $scope.filterDate = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterNumberPro = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showPro= function() {
        $scope.filterNumberPro = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterKBN = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showKBN= function() {
        $scope.filterKBN = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterNumberPro = false;
        $scope.filterBusinessSector = false;
    };
    $scope.showBusiness= function() {
        $scope.filterBusinessSector = true;
        $scope.filterNameClient = false;
        $scope.filterNameTrader = false;
        $scope.filterNameTeam = false;
        $scope.filterProvider = false;
        $scope.filterDate = false;
        $scope.filterNumberPro = false;
        $scope.filterKBN = false;
    };

    $timeout(function () {
        $scope.showInfo = true;
        $scope.load = false;
    }, 1500);


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
        $rootScope.listClient = 'Nie udało się pobrać listy handlowców.'
    });

    $scope.runService = function () {
        ItemsService.findAllItems();
    };
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