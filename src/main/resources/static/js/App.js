/**
 * Created by Promar on 10.09.2016.
 */
'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('myApp', [
    'ngRoute', 'ngResource','ngDialog', 'tableSort', 'ngMaterial', 'ngMessages', 'timer', 'config'

]).config(function ($routeProvider, $httpProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'views/main.html',
            controller: 'LoginCtrl'
        })
        .when('/main', {
            templateUrl: 'views/main.html',
            controller: 'LoginCtrl'
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
            templateUrl: 'views/admin/add_document.html',
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
            templateUrl: 'views/admin/show_documents.html',
            controller: 'ShowAllDocuments'
        })
        .when('/add_client', {
            templateUrl: 'views/admin/add_client.html',
            controller: 'ClientOperation'
        })
        .when('/add_trader', {
            templateUrl: 'views/admin/add_trader.html',
            controller: 'TraderOperation'
        })
        .when('/correct_document', {
            templateUrl: 'views/admin/correct_document.html',
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
            templateUrl: 'views/admin/accept_account.html',
            controller: 'MainAccountCtrl'
        })
        .when('/block_account', {
            templateUrl: 'views/admin/account_management.html',
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
            templateUrl: 'views/admin/trader.html',
            controller: 'UserAccount'
        })
        .when('/all_traders', {
            templateUrl: 'views/admin/all_traders.html',
            controller: 'TraderOperation'
        })
        .otherwise({redirectTo: '/'});

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});
