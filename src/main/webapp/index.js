phonon.options({
    navigator: {
        defaultPage: 'index',
        animatePages: true,
        enableBrowserBackButton: true,
        templateRootDirectory: './tpl'
    },
    i18n: null
});

var app = phonon.navigator();

app.on({page: 'index', preventClose: false, content: null});
app.on({page: 'message', preventClose: true, content: null, readyDelay: 1});
app.on({page: 'sign-in', preventClose: true, content: null, readyDelay: 1});
app.on({page: 'sign-up', preventClose: true, content: null, readyDelay: 1});
app.on({page: 'reset-password', preventClose: true, content: null, readyDelay: 1});

app.start();