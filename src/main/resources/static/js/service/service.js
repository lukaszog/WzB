/**
 * Created by Promar on 28.10.2016.
 */
app.service('userAccount', function ($rootScope, $http, $location) {

    var self = this;


});

app.service('documentWZ', function ($rootScope, $http) {

    var self = this;
    $rootScope.responseFromServer = '';


    this.addWZ = function (numberWZ, subProcess, client, clientNumber, traderName,
                           date) {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/saveDocument',
            data: {
                "numberWZ": numberWZ,
                "subProcess": subProcess,
                "client": client,
                "clientNumber": clientNumber,
                "traderName": traderName,
                "date": date
            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $rootScope.responseFromServer = "Dodałeś dokument WZ o numerze:"+data.numberWZ+" do bazy danych.";

            }).error(function (data) {
            $rootScope.responseFromServer = data.message;

        });
    };

    this.removeWZ = function (numberWZ) {

        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/deleteDocument',
            data: numberWZ,
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                console.log("Usunięty dokument");
                $scope.info = data;

            }).error(function (data) {
            console.log('error:');
            $scope.info = "Błąd dodwania dokumentu!";

        });
    };

    this.findDocumentByNumberWZ = function (numberWZ, subPro) {
        $rootScope.documents = [];
        $http({
            method: 'POST',
            url: 'http://localhost:8080/findByNumber',
            data: {
                "numberWZ": numberWZ,
                "subPro": subPro
            },
            headers: {'Content-type': 'application/json'}
        }).success(function (data) {
            if (data.length == 0) {
                $rootScope.info = "Dokument nie istnieje w bazie danych.";
            }
            console.log(data);
            $rootScope.documents.push(data);
            console.log(numberWZ+"1");
            console.log(subPro+"2");
        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');
            console.log(numberWZ+"1");
            console.log(subPro+"2");
        });
    };

    this.findByClientName = function (client) {
        $rootScope.documents = [];

        $http({
            method: 'POST',
            url: 'http://localhost:8080/findByClient',
            data: client,
            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.documents = data;

        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');

        });
    };

    this.findByClientNr = function (clientNumber) {
        $rootScope.documents = [];

        $http({
            method: 'POST',
            url: 'http://localhost:8080/findByClientNumber',
            data: clientNumber,

            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.documents = data;

        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');

        });
    };

    this.findByTrader = function (traderName) {
        $rootScope.documents = [];

        $http({
            method: 'POST',
            url: 'http://localhost:8080/findByTraderName',
            data: traderName,

            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.documents = data;

        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');

        });
    };

});