/**
 * Created by Promar on 03.11.2016.
 */

app.controller('ClientOperation', ['$scope', '$rootScope', '$http','$route', 'ClientService', 'HOST', 'ngDialog', function ($scope, $rootScope, $http, $route, ClientService, HOST, ngDialog) {

    $scope.form = {};
    $scope.clients = [];
    $scope.editData = {};
    $scope.names = ["STA", "STB", "STC",
        "STE"];

    $http({
        method: 'GET',
        url: HOST + '/all_client',

        headers: {'Content-type': 'application/json'},
    }).success(function (data) {
        $scope.clients = data;
        console.log('asdasd');

    }).error(function (data) {
        console.log('Nie udało się pobrać WZ');

    });

    $scope.reloadRoute = function () {
        $route.reload();
    };

    $scope.createAccountClient = function () {

        var nameClient = $scope.form.nameClient;
        var numberClient = $scope.form.numberClient;
        var nameTeam;

        if ($scope.nameTeam == 'STA') {
            nameTeam = 'STA';
        } else if ($scope.nameTeam == 'STB') {
            nameTeam = 'STB';
        } else if ($scope.nameTeam == 'STC') {
            nameTeam = 'STC';
        } else {
            nameTeam = 'STE';
        }

        ClientService.addClient(nameClient, numberClient, nameTeam);
    };

    $scope.deleteClient = function () {
        $rootScope.nameClient = $scope.editData.accounts.name;

        ngDialog.open({
            template: 'DeleteClient',
            controller: 'ClientOperation',
            className: 'ngdialog-theme-default'
        });
    };

    $scope.deleteClientConfirm = function () {
        console.log('siema');
        ClientService.deleteClient($rootScope.nameClient);
    };

}]);