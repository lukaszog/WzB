/**
 * Created by Promar on 03.11.2016.
 */

app.service('TraderService', function ($rootScope, $http, ngDialog) {

    this.addTrader = function (name, surname, nameTeam) {

        $http({
            method: 'POST',
            url: 'http://localhost:8080/save_trader',
            data: {
                "name": name,
                "surname": surname,
                "nameTeam": nameTeam
            },
            headers: {'Content-type': 'application/json'}
        })
            .success(function (data) {

                $rootScope.responseFromServer = 'Dodałeś pomyślnie handlowca: ' + data.name + " " + data.surname;

                ngDialog.open({
                    template: 'addTrader',
                    controller: 'TraderOperation',
                    className: 'ngdialog-theme-default'
                });

            }).error(function (data) {

            ngDialog.open({
                template: 'error',
                controller: 'TraderOperation',
                className: 'ngdialog-theme-default'
            });

        });
    };

});
