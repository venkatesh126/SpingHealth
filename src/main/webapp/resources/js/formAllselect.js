(function($) {

    $.fn.InfoSelect = function(options) {

        if (typeof options == 'string') {
            var settings = options;
        }
        else {
            var settings = $.extend({
                placeholder: 'Select',
                numDisplayed: 1,
                overflowText: '{n} selected',
                searchText: 'Search',
                showSearch: true
            }, options);
        }


        /**
         * Constructor
         */
        function InfoSelect(select, settings) {
            this.$select = $(select);
            this.settings = settings;
            this.create();
        }


        /**
         * Prototype class
         */
        InfoSelect.prototype = {
            create: function() {
                this.settings.multiple = this.$select.is('[multiple]');
                var multiple = this.settings.multiple ? ' multiple' : '';
                this.$select.wrap('<div class="fs-wrapinfo' + multiple + '" tabindex="0" />');
                this.$select.before('<div class="fs-label-wrapinfo"><div class="fs-labelinfo">' + this.settings.placeholder + '</div><span class="fs-arrow"></span></div>');
                this.$select.before('<div class="fs-dropdowninfo hidden"><div class="fs-optionsinfo"></div></div>');
                this.$select.addClass('hidden');
                this.$wrap = this.$select.closest('.fs-wrapinfo');
                this.$wrap.data('id', window.InfoSelect.num_items);
                window.InfoSelect.num_items++;
                this.reload();
            },
            reload: function() {
                if (this.settings.showSearch) {
                    var search = '<div class="fs-searchinfo"><input type="search" class="searching"  onblur="clearsearchfield();" placeholder="' + this.settings.searchText + '" /></div>';
                    this.$wrap.find('.fs-dropdowninfo').prepend(search);
                }
                this.idx = 0;
                this.optgroup = 0;
                this.selected = [].concat(this.$select.val()); // force an array
                var choices = this.buildOptions(this.$select);
                this.$wrap.find('.fs-optionsinfo').html(choices);
                this.reloadDropdownLabel();
            },
            destroy: function() {
                this.$wrap.find('.fs-label-wrapinfo').remove();
                this.$wrap.find('.fs-dropdowninfo').remove();
                this.$select.unwrap().removeClass('hidden');
            },
            buildOptions: function($element) {
                var $this = this;

                var choices = '';
                $element.children().each(function(i, el) {
                    var $el = $(el);

                    if ('optgroup' == $el.prop('nodeName').toLowerCase()) {
                        //choices += '<div class="fs-optgroup">';
                        choices += '<div class="fs-optgroup-labelinfo" data-group="' + $this.optgroup + '">' + $el.prop('label') + '</div>';
                        choices += $this.buildOptions($el);
                        $this.optgroup++;
                        //choices += '</div>';
                    }
                    else {
                        var val = $el.prop('value');
//                            choices += '<div class="fs-option' + selected + disabled + group +  firstselectvalue + '" id="' + val + '" data-value="' + val + '" data-index="' + $this.idx + '"><input type="hidden" value="' + val + '"  name="myselectbox"/><span class="fs-checkbox"><i></i></span><div class="fs-option-label">' + $el.html() + '</div></div>';

                        // exclude the first option in multi-select mode
                        if (0 < $this.idx || '' != val || !$this.settings.multiple) {
                            var disabled = $el.is(':disabled') ? ' disabled' : '';
                            var selected = -1 < $.inArray(val, $this.selected) ? ' selected' : '';
                            var group = ' g' + $this.optgroup;
                            var firstselectvalue = ' firstselval' + val;
                            choices += '<div class="fs-optioninfo' + selected + disabled + group + firstselectvalue + '" id="selected' + val + '" data-value="' + val + '" data-index="' + $this.idx + '"><input type="hidden" value="' + val + '"  name="myselectbox"/><span class="fs-checkboxinfo"><i></i></span><div class="fs-option-labelinfo">' + $el.html() + '</div></div>';
                           
                        }
                    }
                });

                return choices;
            },
            reloadDropdownLabel: function() {
                var settings = this.settings;
                var labelText = [];

                this.$wrap.find('.fs-optioninfo.selected').each(function(i, el) {
                    labelText.push($(el).find('.fs-option-labelinfo').text());
                });

                if (labelText.length < 1) {
                    labelText = settings.placeholder;
                }
                else if (labelText.length > settings.numDisplayed) {
                    labelText = settings.overflowText.replace('{n}', labelText.length);
                }
                else {
                    labelText = labelText.join(', ');
                }

                this.$wrap.find('.fs-labelinfo').html(labelText);
                this.$select.change();
            }
        }


        /**
         * Loop through each matching element
         */
        return this.each(function() {
            var data = $(this).data('Select');

            if (!data) {
                data = new InfoSelect(this, settings);
                $(this).data('Select', data);
            }

            if (typeof settings == 'string') {
                data[settings]();
            }
        });
    }


    /**
     * Events
     */
    window.InfoSelect = {
        'num_items': 0,
        'active_id': null,
        'active_el': null,
        'last_choice': null,
        'idx': -1
    };

    $(document).on('click', '.fs-optioninfo:not(.hidden, .disabled)', function(e) {

        var $wrap = $(this).closest('.fs-wrapinfo');
        var do_close = false;

        if ($wrap.hasClass('multiple')) {
            var selected = [];

            // shift + click support
            if (e.shiftKey && null != window.InfoSelect.last_choice) {
                var current_choice = parseInt($(this).attr('data-index'));
                var do_select = !$(this).hasClass('selected');
                var min = Math.min(window.InfoSelect.last_choice, current_choice);
                var max = Math.max(window.InfoSelect.last_choice, current_choice);

                for (i = min; i <= max; i++) {
                    $wrap.find('.fs-optioninfo[data-index=' + i + ']')
                            .not('.hidden, .disabled')
                            .each(function() {
                                do_select ?
                                        $(this).addClass('selected') :
                                        $(this).removeClass('selected');
                            });
                }
            }
            else {
                window.InfoSelect.last_choice = parseInt($(this).attr('data-index'));
                $(this).toggleClass('selected');
            }

            $wrap.find('.fs-optioninfo.selected').each(function(i, el) {
                selected.push($(el).attr('data-value'));
            });
        }
        else {
            var selected = $(this).attr('data-value');

            $wrap.find('.fs-optioninfo').removeClass('selected');
            $(this).addClass('selected');
            do_close = true;
        }

        $wrap.find('select').val(selected);
        $wrap.find('select').InfoSelect('reloadDropdownLabel');

        if (do_close) {
            closeDropdown($wrap);
        }
    });

    $(document).on('keyup', '.fs-searchinfo input', function(e) {
        if (40 == e.which) { // down
            $(this).blur();
            return;
        }

        var $wrap = $(this).closest('.fs-wrapinfo');
        var matchOperators = /[|\\{}()[\]^$+*?.]/g;
        var keywords = $(this).val().replace(matchOperators, '\\$&');

        $wrap.find('.fs-optioninfo, .fs-optgroup-labelinfo').removeClass('hidden');

        if ('' != keywords) {
            $wrap.find('.fs-optioninfo').each(function() {
                var regex = new RegExp(keywords, 'gi');
                if (null === $(this).find('.fs-option-labelinfo').text().match(regex)) {
                    $(this).addClass('hidden');
                }
            });

            $wrap.find('.fs-optgroup-labelinfo').each(function() {
                var group = $(this).attr('data-group');
                var num_visible = $(this).closest('.fs-optionsinfo').find('.fs-optioninfo.g' + group + ':not(.hidden)').length;
                if (num_visible < 1) {
                    $(this).addClass('hidden');
                }
            });
        }

        setIndexes($wrap);
    });

    $(document).on('click', function(e) {
        var $el = $(e.target);
        var $wrap = $el.closest('.fs-wrapinfo');

        if (0 < $wrap.length) {

            // user clicked another InfoSelect box
            if ($wrap.data('id') !== window.InfoSelect.active_id) {
                closeDropdown();
            }

            // InfoSelect box was toggled
            if ($el.hasClass('fs-labelinfo') || $el.hasClass('fs-arrowinfo')) {
                var is_hidden = $wrap.find('.fs-dropdowninfo').hasClass('hidden');

                if (is_hidden) {
                    openDropdown($wrap);
                }
                else {
                    closeDropdown($wrap);
                }
            }
        }
        // clicked outside, close all InfoSelect boxes
        else {
            closeDropdown();
        }
    });

    $(document).on('keydown', function(e) {
        var $wrap = window.InfoSelect.active_el;
        var $target = $(e.target);

        // toggle the dropdown on space
        if ($target.hasClass('fs-wrapinfo')) {
            if (32 == e.which) {
                $target.find('.fs-labelinfo').trigger('click');
                return;
            }
        }
        // preserve spaces during search
        else if (0 < $target.closest('.fs-searchinfo').length) {
            if (32 == e.which) {
                return;
            }
        }
        else if (null === $wrap) {
            return;
        }

        if (38 == e.which) { // up
            e.preventDefault();

            $wrap.find('.fs-optioninfo.hl').removeClass('hl');

            var $current = $wrap.find('.fs-optioninfo[data-index=' + window.InfoSelect.idx + ']');
            var $prev = $current.prevAll('.fs-optioninfo:not(.hidden, .disabled)');

            if ($prev.length > 0) {
                window.InfoSelect.idx = parseInt($prev.attr('data-index'));
                $wrap.find('.fs-optioninfo[data-index=' + window.InfoSelect.idx + ']').addClass('hl');
                setScroll($wrap);
            }
            else {
                window.InfoSelect.idx = -1;
                $wrap.find('.fs-searchinfo input').focus();
            }
        }
        else if (40 == e.which) { // down
            e.preventDefault();

            var $current = $wrap.find('.fs-optioninfo[data-index=' + window.InfoSelect.idx + ']');
            if ($current.length < 1) {
                var $next = $wrap.find('.fs-optioninfo:not(.hidden, .disabled):first');
            }
            else {
                var $next = $current.nextAll('.fs-option:not(.hidden, .disabled)');
            }

            if ($next.length > 0) {
                window.InfoSelect.idx = parseInt($next.attr('data-index'));
                $wrap.find('.fs-optioninfo.hl').removeClass('hl');
                $wrap.find('.fs-optioninfo[data-index=' + window.InfoSelect.idx + ']').addClass('hl');
                setScroll($wrap);
            }
        }
        else if (32 == e.which || 13 == e.which) { // space, enter
            e.preventDefault();

            $wrap.find('.fs-optioninfo.hl').click();
        }
        else if (27 == e.which) { // esc
            closeDropdown($wrap);
        }
    });

    function setIndexes($wrap) {
        $wrap.find('.fs-optioninfo.hl').removeClass('hl');
        $wrap.find('.fs-searchinfo input').focus();
        window.InfoSelect.idx = -1;
    }

    function setScroll($wrap) {
        var $container = $wrap.find('.fs-optionsinfo');
        var $selected = $wrap.find('.fs-optioninfo.hl');

        var itemMin = $selected.offset().top + $container.scrollTop();
        var itemMax = itemMin + $selected.outerHeight();
        var containerMin = $container.offset().top + $container.scrollTop();
        var containerMax = containerMin + $container.outerHeight();

        if (itemMax > containerMax) { // scroll down
            var to = $container.scrollTop() + itemMax - containerMax;
            $container.scrollTop(to);
        }
        else if (itemMin < containerMin) { // scroll up
            var to = $container.scrollTop() - containerMin - itemMin;
            $container.scrollTop(to);
        }
    }

    function openDropdown($wrap) {
        window.InfoSelect.active_el = $wrap;
        window.InfoSelect.active_id = $wrap.data('id');
        window.InfoSelect.initial_values = $wrap.find('select').val();
        $wrap.find('.fs-dropdowninfo').removeClass('hidden');
        setIndexes($wrap);
    }

    function closeDropdown($wrap) {
        if ('undefined' == typeof $wrap && null != window.InfoSelect.active_el) {
            $wrap = window.InfoSelect.active_el;
        }
        if ('undefined' !== typeof $wrap) {
            // only trigger if the values have changed
            var initial_values = window.InfoSelect.initial_values;
            var current_values = $wrap.find('select').val();
            if (JSON.stringify(initial_values) != JSON.stringify(current_values)) {
                $(document).trigger('fs:changed', $wrap);
            }
        }

        $('.fs-dropdowninfo').addClass('hidden');
        window.InfoSelect.active_el = null;
        window.InfoSelect.active_id = null;
        window.InfoSelect.last_choice = null;
    }

})(jQuery);