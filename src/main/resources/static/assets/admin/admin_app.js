var app = angular.module("app",["ngRoute"]);
app.config(function($routeProvider) {
  $routeProvider
  .when("/product",{
      templateUrl:"/assets/admin/product/index.html",
      controller:"product_ctrl"
  })
  .when("/authorize",{
      templateUrl:"/assets/admin/authority/index.html",
      controller:"authority_ctrl"
    })
  .when("/unauthorized",{
      templateUrl:"/assets/admin/authority/unauthorized.html",
      controller:"unauthorized_ctrl"})
  .otherwise({template:" <h1 class='text-center'>FPT Polytechnic Administration</h1> "});
})
app.controller("product_ctrl",function($scope,$http){
    $scope.items=[];
    $scope.cates=[];
    $scope.form={};
    $scope.initialize=function(){
        $http.get("/rest/products").then(resp=>{
            $scope.items=resp.data
            $scope.items.forEach(item=>{
                item.createDate=new Date(item.createDate)
            })
        });
        $http.get("/rest/categories").then(resp=>{
            $scope.cates=resp.data
        });
    }
    $scope.initialize();
    $scope.reset=function(){

    }
    $scope.edit=function(item){
        $scope.form=angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show')

    }
    $scope.create=function(){
        var item=angular.copy($scope.form);
        $http.post('/rest/products',item).then(resp=>{
            resp.data.createDate   =new Date(resp.data.createDate)
            $scope.items.push(resp.data)
            $scope.reset()

        }).catch(error=>{
                alert("error")
                console.log("error",error)
        })
    }
    $scope.reset=function(){
        $scope.form={
            createDate:new Date(),
            image:'cloud-upload.jpg',
            available:true,
        }
    }
    $scope.update=function(){
        var item=angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`,item).then(resp=>{
            var index=$scope.items.findIndex(p=> p.id == item.id);
            $scope.items[index]=item;
            alert("update success")
        }).catch(error=>{
                alert("error")
                console.log("error",error)
        })
    }
    $scope.delete=function(item){
        $http.delete(`/rest/products/${item.id}`).then(resp=>{
            var index=$scope.items.findIndex(p=> p.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert("delete success")
        }).catch(error=>{
                alert("error")
                console.log("error",error)
        })

    }
    $scope.imageChanged=function(files){
        var data=new FormData();
        data.append('file',files[0]);
        $http.post('/rest/upload/images',data,{
            transformRequest:angular.identity,
            headers:{'Content-Type':undefined}
        }).then(resp=>{
            $scope.form.image=resp.data.name;
        }).catch(error=>{
            alert("failed")
            console.log("error",error);
        });

    }
    $scope.pager={
            page:0,
            size: 10,
            get items(){
                var start =this.page * this.size;
                return $scope.items.slice(start, start + this.size);
            },
            get count(){
                    return Math.ceil(1.0 * $scope.items.length/this.size);
            },
            first(){
                    this.page=0
            },
            prev(){
                this.page--;
                if(this.page<0){
                    this.last();
                }
        }, 
            next(){
            this.page++;
            if(this.page>=this.count){
                this.first();
            }
        },
        last(){
            this.page=this.count-1;
        }
    }
});
app.controller("authority_ctrl",function($scope,$http,$location){
    $scope.roles=[];
    $scope.admins=[];
    $scope.authorities=[];

    $scope.authority_of=function(acc,role){
        if($scope.authorities){
            return $scope.authorities.find(ur => ur.account.username  == acc.username && ur.role.id == role.id);
        }
    }

    $scope.authority_changed=function(acc,role){
        var authority=$scope.authority_of(acc, role);
        if(authority){
            $scope.revoke_authority(authority)
        }else{
            authority={account:acc,role:role};
            $scope.grant_authority(authority)
        }
    }
    $scope.grant_authority=function(authority){
        $http.post(`/rest/authorities`,authority).then(resp=>{
            $scope.authorities.push(resp.data);
            alert("success")
        }).catch(error=>{
            alert("fail")
            console.log("error",error);
        })
    }
    $scope.revoke_authority=function(authority){
        $http.delete(`/rest/authorities/${authority.id}`).then(resp=>{
            var index=$scope.authorities.findIndex(a=>a.id==authority.id);
            $scope.authorities.splice(index, 1);
            alert("success")
        }).catch(error=>{
            alert("fail")
            console.log("error",error);
        })
    }



    $scope.initialize=function(){
        $http.get("/rest/roles").then(resp=>{
            $scope.roles=resp.data;
         
        })
        $http.get("/rest/accounts?admin=true").then(resp=>{
            $scope.admins=resp.data;
        })
        $http.get("/rest/authorities?admin=true").then(resp=>{
            $scope.authorities=resp.data;


        }).catch(error=>{
             $location.path("/unauthorized")
        })

    }
    $scope.initialize();

})