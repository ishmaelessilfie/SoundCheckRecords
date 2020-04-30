/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Document */

var project = {};

var swalInit = swal.mixin({
    buttonsStyling: false,
    confirmButtonClass: 'btn btn-primary',
    cancelButtonClass: 'btn btn-light'
});

$('document').ready(function () {

//EDIT PROJECT..............................................
   $('.editbtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (projList, status) {
            $('#id').val(projList.id);
            $('#number').val(projList.number);
            $('#artistename').val(projList.artistename);
            $('#songtitle').val(projList.songtitle);
            $('#projecttype').val(projList.projecttype.id);
            $('#activitytype').val(projList.activitytype.id);
            $('#combination').val(projList.combination.id);
            $('#genre').val(projList.genre.id);
            $('#writer').val(projList.writer);
            $('#producer').val(projList.producer);
            $('#engineer').val(projList.engineer);
            $('#projectstartdate').val(projList.projectstartdate);
            $('#town').val(projList.town);
            $('#budget').val(projList.budget);
            $('#email').val(projList.email);
            $('#country').val(projList.country);
            $('#phone').val(projList.phone);
        });
        $('#EditRecord').modal();
    });
//END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,


//VIEW PROJECT......................................
    $('.viewbtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (List, status) {
            $('#idd').val(List.id);
            $('#artistenameview').val(List.artistename);
            $('#songtitleview').val(List.songtitle);
            $('#projecttypeview').val(List.projecttype.id);
            $('#activitytypeview').val(List.activitytype.id);
            $('#combinationview').val(List.combination.id);
            $('#genreview').val(List.genre.id);
            $('#writerview').val(List.writer);
            $('#producerview').val(List.producer);
            $('#engineerview').val(List.engineer);
            $('#projectstartdateview').val(List.projectstartdate);
            $('#townview').val(List.town);
            $('#budgetview').val(List.budget);
            $('#emailview').val(List.email);
            $('#countryview').val(List.country);
            $('#phoneview').val(List.phone);
        });
        $('#ViewDetail').modal();
    });
//END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

//EDIT INVOICE..........................................................
    $('.editbtninv').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (projList, status) {
            $('#idinv').val(projList.id);
            $('#projectid').val(projList.project.id);
            $('#studiotimecost').val(projList.studiotimecost);
            $('#timeinhr').val(projList.timeinhr);
            $('#mixingcost').val(projList.mixingcost);
            $('#masteringcost').val(projList.masteringcost);
            $('#costofintruments').val(projList.costofintruments);
        });
        $('#EditInvoice').modal();
    });
   //END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
   
   //ADD INVOICE........................................
    $('.invoicebtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (projList, status) {
            $('#myid').val(projList.id);
        });
        $('#AddInvoice').modal();
        $('#eer').on('click', function () {
            swalInit.fire({
                title: 'Good job!',
                text: 'Invoice added successfully!',
                type: 'success',
                showCloseButton: true
            });
        });
    });
//END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

//ADD USER................................................
    $(' #photoButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#photoModal #employeePhoto').attr('src', href);
        $('#photoModal').modal();
    });
    $("#btnSubmitt").click(function () {
        var form = $('#fileUploadForm')[0];
        var data = new FormData(form);
        var jsonDataObj = {
            "username": $("#usernamee").val(),
            "password": $("#passwordd").val()
        };
        data.append("empJson", JSON.stringify(jsonDataObj));
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/profile",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                swalInit.fire({
                    title: 'Good job!',
                    text: 'User added successfully!',
                    type: 'success',
                    showCloseButton: true
                });
                $('#AddUsers').modal('hide');
            },
            error: function (e) {

                console.log("ERROR : ", e);
            }
        });
    });
//END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

//UPDATE LOOGEDIN USER PROFILE......................................
    $('.profilebtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (profile, status) {
            $('#idup').val(profile.id);
            $('#username').val(profile.username);
            $('#password').val(profile.password);
            $('#datecreat').val(profile.datecreated);
        });
        $('#ViewProfile').modal();
    });
    $("#btnSubmitprofileupd").click(function () {
        var form = $('#fileUploadFormup')[0];
        var data = new FormData(form);
        var jsonDataObj = {
            "id": $("#idup").val(),
            "username": $("#username").val(),
            "password": $("#password").val(),
            "datecreated": $("#datecreat").val()
        };
        data.append("empJson", JSON.stringify(jsonDataObj));
        $.ajax({
            type: "PUT",
            enctype: 'multipart/form-data',
            url: "/updateprofile",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                swalInit.fire({
                    title: 'Good job!',
                    text: 'User profile updated successfully!',
                    type: 'success',
                    showCloseButton: true
                });
                $('#ViewProfile').modal('hide');

            }
        });
    });
  //END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
    
    
  //UPDATE APP USER....................................................  
 $('.updateuser').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (userList, status) {
            $('#idupdd').val(userList.id);
            $('#usernamer').val(userList.username);
            $('#passwordd').val(userList.password);
            $('#datecreatt').val(userList.datecreated);
        });
        $('#updateProfile').modal();
  });    
$("#btnSubmitprofileupdd").click(function () {
        var form = $('#fileUploadFormuppp')[0];
        var data = new FormData(form);
        var jsonDataObj = {
            "id": $("#idupdd").val(),
            "username": $("#usernamer").val(),
            "password": $("#passwordd").val(),
            "datecreated": $("#datecreatt").val()
        };
        data.append("empJson", JSON.stringify(jsonDataObj));
        $.ajax({
            type: "PUT",
            enctype: 'multipart/form-data',
            url: "/updateprofile",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                swalInit.fire({
                    title: 'Good job!',
                    text: 'User updated successfully!',
                    type: 'success',
                    showCloseButton: true
                });
                $('#updateProfile').modal('hide');
            }
        });
    });
    //END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
    
    
   //DELETE INVOICE............................................ 
  $(Document).on('click', '#btDelete', function () {
        project.deleteInvoice($(this).data("id"));
    });
 project.deleteInvoice = function (res) {
        url = "/delete_invoice/";

        swalInit.fire({
            title: 'Are you sure?',
            text: "You won't be able to recover this invoice!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
            buttonsStyling: false
        }).then(function (result) {
            if (result.value) {
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: url + res,
                    success: function (callback) {
                         
                        swalInit.fire(
                                'Deleted!',
                                'Your record has been deleted.',
                                'success'
                                );
                        table.row( $button.parents('tr') ).remove().draw();
                    }
                     
                });
            } else if (result.dismiss === swal.DismissReason.cancel) {
                swalInit.fire(
                        'Cancelled',
                        'Your imaginary file is safe :)',
                        'error'
                 );
            }
        });
    };
    //END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
   
 //DELETE PROJECT...........................................
$(Document).on('click', '#deleteprojbtn', function () {
        project.deleteProject($(this).data("id"));
    });
 project.deleteProject = function (res) {
        url = "/delete_project/";

        swalInit.fire({
            title: 'Are you sure?',
            text: "You won't be able to recover this invoice!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
            buttonsStyling: false
        }).then(function (result) {
            if (result.value) {
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: url + res,
                    success: function () {
                        swalInit.fire(
                                'Deleted!',
                                'Your record has been deleted.',
                                'success'
                                );
                    }
                });
            } else if (result.dismiss === swal.DismissReason.cancel) {
                swalInit.fire(
                        'Cancelled',
                        'Your imaginary file is safe :)',
                        'error'
                 );
            }
        });
    };
    //END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
    
    //DELETE USER.................................
$(Document).on('click', '.deletebtnuser', function () {
        project.deleteUsers($(this).data("id"));
    });
 project.deleteUsers = function (res) {
        url = "/delete_user/";
        swalInit.fire({
            title: 'Are you sure?',
            text: "You won't be able to recover this invoice!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
            buttonsStyling: false
        }).then(function (result) {
            if (result.value) {
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: url + res,
                    success: function () {
                        swalInit.fire(
                                'Deleted!',
                                'Your record has been deleted.',
                                'success'
                                );
                    }
                });
            } else if (result.dismiss === swal.DismissReason.cancel) {
                swalInit.fire(
                        'Cancelled',
                        'Your imaginary file is safe :)',
                        'error'

                        );
            }
        });
    };
//END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
});






    