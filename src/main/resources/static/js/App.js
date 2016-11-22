/**
 * Created by Promar on 10.09.2016.
 */
'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('myApp', [
    'ngRoute', 'ngResource', 'ngDialog', 'tableSort', 'ngMaterial', 'ngMessages', 'timer', 'config'

])
    .factory('httpInterceptor', function ($q, $rootScope, $location) {
        return {
            request: function (config) {
                return config || $q.when(config)
            },
            response: function (response) {
                return response || $q.when(response);
            },
            responseError: function (response) {
                if (response.status === 401) {
                    //here I preserve login page
                    $location.url('/main');
                    $rootScope.$broadcast('error');
                }
                return $q.reject(response);
            }
        };
    })
    .config(function ($routeProvider, $httpProvider) {

        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'UserAccount'
            })
            .when('/main', {
                templateUrl: 'views/main.html',
                controller: 'UserAccount'
            })
            .when('/home', {
                templateUrl: 'views/home.html',
                controller: 'UserAccount'
            })
            .when('/search', {
                templateUrl: 'views/find_document.html',
                controller: 'findDocument'
            })
            .when('/addDocument', {
                templateUrl: 'views/admin/add/add_document.html',
                controller: 'DocumentOperation'
            })
            .when('/admin', {
                templateUrl: 'views/admin/admin.html',
                controller: 'AdminController'
            })
            .when('/account', {
                templateUrl: 'views/account/account.html',
                controller: 'UserAccount'
            })
            .when('/show_documents', {
                templateUrl: 'views/admin/show_all/show_documents.html',
                controller: 'ShowAllDocuments'
            })
            .when('/add_client', {
                templateUrl: 'views/admin/add/add_client.html',
                controller: 'ClientOperation'
            })
            .when('/add_trader', {
                templateUrl: 'views/admin/add/add_trader.html',
                controller: 'TraderOperation'
            })
            .when('/correct_document', {
                templateUrl: 'views/admin/show_all/correct_document.html',
                controller: 'AdminController'
            })
            .when('/login', {
                templateUrl: 'views/login.html',
                controller: 'LoginCtrl',
                controllerAs: 'controller'
            })
            .when('/register', {
                templateUrl: 'views/register/register.html',
                controller: 'RegisterCtrl'
            })
            .when('/accept_account', {
                templateUrl: 'views/admin/account/accept_account.html',
                controller: 'MainAccountCtrl'
            })
            .when('/block_account', {
                templateUrl: 'views/admin/account/account_management.html',
                controller: 'MainAccountCtrl'
            })
            .when('/user_document', {
                templateUrl: 'views/user_info/all_user_document.html',
                controller: 'UserAccount'
            })
            .when('/change_password', {
                templateUrl: 'views/user_info/change_password.html',
                controller: 'UserAccount'
            })
            .when('/after_register', {
                templateUrl: 'views/register/after_register.html',
                controller: 'UserAccount'
            })
            .when('/trader', {
                templateUrl: 'views/admin/add/trader.html',
                controller: 'UserAccount'
            })
            .when('/all_traders', {
                templateUrl: 'views/admin/show_all/all_traders.html',
                controller: 'TraderOperation'
            })
            .when('/client', {
                templateUrl: 'views/admin/add/client.html',
                controller: 'ClientOperation'
            })
            .when('/all_client', {
                templateUrl: 'views/admin/show_all/all_client.html',
                controller: 'ClientOperation'
            })
            .when('/history', {
                templateUrl: 'views/history/history.html',
                controller: 'HistoryCtrl'
            })
            .when('/all_delete_document', {
                templateUrl: 'views/history/all_delete_document.html',
                controller: 'HistoryCtrl'
            })

            .otherwise({redirectTo: '/'});

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $httpProvider.interceptors.push('httpInterceptor');

    });
