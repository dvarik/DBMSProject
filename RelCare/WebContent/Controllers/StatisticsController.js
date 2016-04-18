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
	$scope.chart4='false';
	
	$scope.selectedBranch = null;
	$scope.branches = {};
	var tbl1 = [];
	
	$scope.charter1 = function(){
		$scope.chart1='true';
		$scope.chart2='false';
		$scope.chart3='false';
		$scope.chart4='false';
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
		$scope.chart4='false';
    	tbl2 = []; 
    		
		getDataSvc.getIllnessStats().then(function(res) {
	        if (res != null) {
        	   tbl2 = res;
		       $scope.il = [];
		       var temp = [];
	    	   $.each(tbl2,function(){
	    		   if(temp.indexOf(this["illnessName"]) == -1)
	    		   {
	    			   var obj = {"illnessName":this["illnessName"]};
	    			   $scope.il.push(obj);
	    			   temp.push(this["illnessName"]);
	    		   }
	    	   });
	    	   temp = [];
	        } else {
	            console.log("Error");
	        }
	    });
    }
    
    $scope.showChartil = function(){
    	var ilArray = [["State","0-5 years","6-12 years","13-19 years","20-40 years","Above 40 years"]];
	    $.each(tbl2, function() {
	    	if(this["illnessName"] == $scope.selectedil.illnessName){
	    		var item = [this["state"], this["below6"], this["six12"], this["thirteen19"], this["twenty40"], this["above40"]];
	 	        ilArray.push(item);
	    	}
	    });
    
    console.log(ilArray);
	var data = google.visualization.arrayToDataTable(ilArray);
      var options = {
        title: ' Age wise Stats for ' + $scope.selectedil.illnessName,
        width: 1000,
        height: 800,
        bar: { groupWidth: '95%' },
        isStacked: true
      };
      var chart = new google.visualization.BarChart(document.getElementById('chartdiv2'));

	  chart.draw(data, options);
    }   
    
    var tbl3 = [];
    
    $scope.charter3 = function(){
        $scope.chart1='false';
    	$scope.chart2='false';
    	$scope.chart3='true';
    	$scope.chart4='false';
    	getDataSvc.getInsuranceStats().then(function(res) {
	   	        if (res != null) {
	   	           tbl3 = res;
	   	           $scope.ils = [];
	   	           var temp = [];
	   	          $.each(tbl3, function() {
	   	        	if(temp.indexOf(this["illnessName"]) == -1){
	   	        		$scope.ils.push({"illnessName":this["illnessName"]});
	   	        		temp.push(this["illnessName"]);
	   	        	}
	   		    	});
	   	          temp = [];
	   	        } else {
	   	            console.log("Error");
	   	        }
	   	    });
    }
    
    $scope.showChartIns = function(){
    	var inArray = [["Branch","Insured Patients count"]];
	    $.each(tbl3, function() {
	    	if(this["illnessName"] == $scope.selectedil.illnessName){
	        var item = [this["branchCity"], this["patientCount"]];
	        inArray.push(item);
	    	}
	    });
    
    console.log(inArray);
	var data = google.visualization.arrayToDataTable(inArray);
      var chart = new google.visualization.PieChart(document.getElementById('chartdiv3'));
      var options = {
    	      title : 'Insured Patients Per Branch for Illness ' + $scope.selectedil.illnessName
    	    };

	  chart.draw(data, options);
    }
    
    var tbl4 = [];
    $scope.charter4 = function(){
    	
    	$scope.chart1='false';
		$scope.chart2='false';
		$scope.chart3='false';
		$scope.chart4='true';
    		
		getDataSvc.getPatientCountStats().then(function(res) {
	        if (res != null) {
        	   tbl4 = res;
		       $scope.brs = [];
		       var temp = [];
	    	   $.each(tbl4,function(){
	    		   if(temp.indexOf(this["branchCity"]) == -1){
	    			   var obj = {"branchCity":this["branchCity"],"branchId":this["branchid"]};
	    			   $scope.brs.push(obj);
	    			   temp.push(this["branchCity"]);
	    		   }
	    	   });
	        } else {
	            console.log("Error");
	        }
	    });
    }
    
    $scope.chart4a = function(){
    	var t = [];
    	$scope.years = [];
	    $.each(tbl4, function() {
	    	if(this["branchid"] == $scope.selectedbrs.branchId){
	    		if(t.indexOf(this["year"]) == -1){
	    			$scope.years.push({"year":this["year"]});
	    			t.push(this["year"]);
	    		}
	    	}
	    });
	    t = [];
    }
	    
    $scope.showChartPat = function(){
    	var inArray = [["Department","Patients count"]];
	    $.each(tbl4, function() {
	    	if(this["branchid"] == $scope.selectedbrs.branchId && this["year"] == $scope.selectedYear.year){
	        var item = [this["deptName"],this["totalPatients"]];
	        inArray.push(item);
	    	}
	    });
    
    console.log(inArray);
	var data = google.visualization.arrayToDataTable(inArray);
      var chart = new google.visualization.LineChart(document.getElementById('chartdiv4'));
      var options = {
    	      title : 'Patients per Dept for branch ' + $scope.selectedbrs.branchCity + ' in ' + $scope.selectedYear.year,
    	      curveType: 'function'
    	    };

	  chart.draw(data, options);
    }

}]);
})(document, window, window.angular);