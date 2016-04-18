(function(document, window, angular) {
    'use strict';

/* Controllers */
google.load('visualization', '1', {
  packages: ['corechart','bar']
});

google.setOnLoadCallback(function() {
  //angular.bootstrap(document.body, ['hospApp']);
});

angular.module('hospApp').controller('StatisticsController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{
	$scope.chart1='false';
	$scope.chart2='false';
	$scope.chart3='false';
	
	$scope.selectedBranch = null;
	$scope.branches = {};
	var tbl1 = [];
	
	$scope.charter1 = function(){
		$scope.chart1='true';
		$scope.chart2='false';
		$scope.chart3='false';
		
		getDataSvc.getBranches().then(function(res) {
	        if (res != null) {
	           $scope.branches = res;
	           getDataSvc.getRevenueStats().then(function(res2) {
	   	        if (res2 != null) {
	   	           tbl1 = res2;
	   	        } else {
	   	            console.log("Error");
	   	        }
	   	    });
	        } else {
	            console.log("Error");
	        }
	    });
		
	}
	
    $scope.showChartBr = function(){
    	var statesArray = [["Department name","Total Revenue"]];
	    $.each(tbl1, function() {
	    	if(this["branchid"] == $scope.selectedBr.branchid){
	        var item = [this["deptName"], this["totalCost"]];
	        statesArray.push(item);
	    	}
    });
    
	var data = google.visualization.arrayToDataTable(statesArray);
		      var options = {
		        title: 'Departmental Performance For Branch: ' + $scope.selectedBr.branchCity
		      };
		      var chart = new google.visualization.ColumnChart(document.getElementById('chartdiv'));
		      chart.draw(data, options);
    }
    
    var tbl2 = [];
    $scope.charter2 = function(){
    	
    	$scope.chart1='false';
		$scope.chart2='true';
		$scope.chart3='false';
    	tbl2 = []; 
    		
		getDataSvc.getIllnessStats().then(function(res) {
	        if (res != null) {
        	   tbl2 = res;
        	   $scope.st = [];
		       $scope.il = [];
		       var temp = [];
	    	   $.each(tbl2,function(){
	    		   if(temp.indexOf(this["state"]) == -1)
	    		   {
	    			   var obj = {"state":this["state"]};
	    			   $scope.st.push(obj);
	    			   temp.push(this["state"]);
	    		   }
	    	   });
	    	   temp = [];
	        } else {
	            console.log("Error");
	        }
	    });
    }
    
    $scope.charter2a = function(){
    	$scope.il = [];
    	 $.each(tbl2,function(){
  		   if($scope.selectedst.state == this["state"])
  		   {
  			   var obj2 = {"illnessName":this["illnessName"]};
  			   $scope.il.push(obj2);
  		   }
  	   });
    }
    
    $scope.showChartil = function(){
    	var ilArray = [["AgeGroup","Count"]];
	    $.each(tbl2, function() {
	    	if(this["illnessName"] == $scope.selectedil.illnessName && this["state"] == $scope.selectedst.state){
	        var item = ["0-5 years", this["below6"]];
	        ilArray.push(item);
	        var item = ["6-12 years", this["six12"]];
	        ilArray.push(item);
	        var item = ["13-19 years", this["thirteen19"]];
	        ilArray.push(item);
	        var item = ["20-40 years", this["twenty40"]];
	        ilArray.push(item);
	        var item = ["Above 40 years", this["above40"]];
	        ilArray.push(item);
	    	}
	    });
    
    console.log(ilArray);
	var data = google.visualization.arrayToDataTable(ilArray);
      var options = {
        title: $scope.selectedst.state + ' stats for illness ' + $scope.selectedil.illnessName
      };
      var chart = new google.visualization.Histogram(document.getElementById('chartdiv2'));

	  chart.draw(data, options);
    }   

}]);
})(document, window, window.angular);