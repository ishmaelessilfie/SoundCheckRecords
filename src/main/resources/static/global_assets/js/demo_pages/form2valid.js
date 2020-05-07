var FormValidatio = function() {

    // Uniform
    var _componentUnifor = function() {
        if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-input-styled').uniform({
            fileButtonClass: 'action btn bg-blue'
        });
    };

    // Switchery
    var _componentSwitcher = function() {
        if (typeof Switchery === 'undefined') {
            console.warn('Warning - switchery.min.js is not loaded.');
            return;
        }

        // Initialize single switch
        var elems = Array.prototype.slice.call(document.querySelectorAll('.form-input-switchery'));
        elems.forEach(function(html) {
            var switchery = new Switchery(html);
        });
    };

    // Bootstrap switch
    var _componentBootstrapSwitc = function() {
        if (!$().bootstrapSwitch) {
            console.warn('Warning - bootstrap_switch.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-input-switch').bootstrapSwitch({
            onSwitchChange: function(state) {
                if(state) {
                    $(this).valid(true);
                }
                else {
                    $(this).valid(false);
                }
            }
        });
    };

    // Touchspin
    var _componentTouchspi = function() {
        if (!$().TouchSpin) {
            console.warn('Warning - touchspin.min.js is not loaded.');
            return;
        }

        // Define variables
        var $touchspinContaine = $('.touchspin-postfix');

        // Initialize
        $touchspinContaine.TouchSpin({
            min: 0,
            max: 100,
            step: 0.1,
            decimals: 2,
            postfix: '%'
        });

        // Trigger value change when +/- buttons are clicked
        $touchspinContaine.on('touchspin.on.startspin', function() {
            $(this).trigger('blur');
        });
    };

    // Select2 select
    var _componentSelect = function() {
        if (!$().select2) {
            console.warn('Warning - select2.min.js is not loaded.');
            return;
        }

        // Initialize
        var $select = $('.form-control-select2').select2({
            minimumResultsForSearch: Infinity
        });

        // Trigger value change when selection is made
        $select.on('change', function() {
            $(this).trigger('blur');
        });
    };

    // Validation config
    var _componentValidatio = function() {
        if (!$().validate) {
            console.warn('Warning - validate.min.js is not loaded.');
            return;
        }

        // Initialize
        var validator = $('.form-validate').validate({
            ignore: 'input[type=hidden], .select2-search__field', // ignore hidden fields
            errorClass: 'validation-invalid-label',
            successClass: 'validation-valid-label',
            validClass: 'validation-valid-label',
            highlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            unhighlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            success: function(label) {
                label.addClass('validation-valid-label').text('Success.'); // remove to hide Success message
            },

            // Different components require proper error label placement
            errorPlacement: function(error, element) {

                // Unstyled checkboxes, radios
                if (element.parents().hasClass('form-check')) {
                    error.appendTo( element.parents('.form-check').parent() );
                }

                // Input with icons and Select2
                else if (element.parents().hasClass('form-group-feedback') || element.hasClass('select2-hidden-accessible')) {
                    error.appendTo( element.parent() );
                }

                // Input group, styled file input
                else if (element.parent().is('.uniform-uploader, .uniform-select') || element.parents().hasClass('input-group')) {
                    error.appendTo( element.parent().parent() );
                }

                // Other elements
                else {
                    error.insertAfter(element);
                }
            },
            rules: {
               
                email: {
                    email: true
                },
                repeat_email: {
                    equalTo: '#email'
                },
                               date: {
                    date: true
                },
                projectstartdate:{
                    date: true
                },
               
                
                numbers: {
                    number: true
                },
                phone:{
                 digits: true ,
                 
                 minlength:10,
                 maxlength:10
                }
                 
               
            }

        });

        // Reset form
        $('#reset').on('click', function() {
            validator.resetForm();
        });
    };
       //
    // Return objects assigned to module
    //

    return {
        init: function() {
            _componentUnifor();
            _componentSwitcher();
            _componentBootstrapSwitc();
            _componentTouchspi();
            _componentSelect();
            _componentValidatio();
        }
    };
}();


// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
    FormValidatio.init();
});




