/**
 * Created by Promar on 03.11.2016.
 */

app.controller('ClientOperation', ['$scope', '$http', 'ClientService', function ($scope, $http, ClientService) {

    $scope.form = {};
    $scope.names = ["STA", "STB", "STC",
        "STE"];

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

}]);