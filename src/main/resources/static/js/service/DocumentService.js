/**
 * Created by Promar on 28.10.2016.
 */

app.service('documentWZ', function ($rootScope, $http, ngDialog) {

    var self = this;
    $rootScope.responseFromServer = '';


    this.addWZ = function (numberWZ, subProcess, client, traderName,
                           date) {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/saveDocument',
            data: {
                "numberWZ": numberWZ,
                "subProcess": subProcess,
                "client": client,
                "traderName": traderName,
                "date": date
            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {
                $rootScope.responseFromServer = "Dodałeś dokument WZ o numerze:" + data.numberWZ + " / " + data.subProcess +
                    " do bazy danych.";

                ngDialog.open({
                    template: 'addDocument',
                    controller: 'DocumentOperation',
                    className: 'ngdialog-theme-default'
                });

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
            console.log(numberWZ + "1");
            console.log(subPro + "2");
        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');
            console.log(numberWZ + "1");
            console.log(subPro + "2");
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

    this.findByNameTeam = function (nameTeam) {
        $rootScope.documents = [];

        $http({
            method: 'POST',
            url: 'http://localhost:8080/find_nameteam',
            data: nameTeam,
            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.documents = data;

        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');

        });
    };


    this.deleteDocument = function (numberWZ, subProcess) {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/deleteDocument',
            data: {
                "numberWZ": numberWZ,
                "subPro": subProcess
            },
            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            console.log('Usunieto dokument');
            ngDialog.open({
                template: 'succes',
                controller: 'findDocument',
                className: 'ngdialog-theme-default'
            });

        }).error(function (data) {
            ngDialog.open({
                template: 'error',
                controller: 'findDocument',
                className: 'ngdialog-theme-default'
            });

        });
    }

    this.correctWZ = function (numberWZ, subPro) {
        $rootScope.documents = [];
        $http({
            method: 'PATCH',
            url: 'http://localhost:8080/by_correct',
            data: {
                "numberWZ": numberWZ,
                "subPro": subPro
            },
            headers: {'Content-type': 'application/json'}
        }).success(function (data) {
            if (data.length == 0) {
                $rootScope.info = "Dokument nie istnieje w bazie danych.";
            }

        }).error(function (data) {

        });
    };

    this.correctWZAll = function () {
        $rootScope.documents = [];

        $http({
            method: 'GET',
            url: 'http://localhost:8080/find_correct',

            headers: {'Content-type': 'application/json'},
        }).success(function (data) {
            $rootScope.documents = data;

        }).error(function (data) {
            console.log('Nie udało się pobrać WZ');

        });

    };



});