/**
 * Created by Promar on 03.11.2016.
 */

app.service('ClientService', function ($rootScope, $http, ngDialog) {

    $rootScope.responseFromServer = '';

    this.addClient = function (name, numberClient, nameTeam) {

        $http({
            method: 'POST',
            url: 'http://localhost:8080/save_client',
            data: {
                "name": name,
                "numberClient": numberClient,
                "nameTeam": nameTeam
            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {

                $rootScope.responseFromServer = 'Dodałeś pomyślnie klienta o naziwe: ' + data.name;

                ngDialog.open({
                    template: 'addClient',
                    controller: 'ClientOperation',
                    className: 'ngdialog-theme-default'
                });

            }).error(function (data) {

                ngDialog.open({
                    template: 'error',
                    controller: 'ClientOperation',
                    className: 'ngdialog-theme-default'
            });

        });
    };


});