/**
 * Created by Promar on 10.09.2016.
 */
'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('myApp', [
    'ngRoute', 'ngResource','ngDialog', 'tableSort', 'timer','ngMaterial', 'ngMessages'

]).config(function ($routeProvider, $httpProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'views/main.html',
            controller: ''
        })
        .when('/main', {
            templateUrl: 'views/main.html',
            controller: ''
        })
        .when('/search', {
            templateUrl: 'views/search.html',
            controller: 'findDocument'
        })
        .when('/addDocument', {
            templateUrl: 'views/add_document.html',
            controller: 'DocumentOperation'
        })
        .when('/admin', {
            templateUrl: 'views/admin.html',
            controller: 'AdminController'
        })
        .when('/account', {
            templateUrl: 'views/account.html',
            controller: ''
        })
        .when('/show_documents', {
            templateUrl: 'views/show_documents.html',
            controller: 'ShowAllDocuments'
        })
        .when('/add_client', {
            templateUrl: 'views/add_client.html',
            controller: 'ClientOperation'
        })
        .when('/add_trader', {
            templateUrl: 'views/add_trader.html',
            controller: 'TraderOperation'
        })
        .when('/correct_document', {
            templateUrl: 'views/correct_document.html',
            controller: 'AdminController'
        })
        .when('/login', {
            templateUrl: 'views/login.html',
            controller: 'DemoCtrl'
        })
        .when('/register', {
            templateUrl: 'views/register.html',
            controller: 'DemoCtrl'
        })
        .otherwise({redirectTo: '/'});

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});
