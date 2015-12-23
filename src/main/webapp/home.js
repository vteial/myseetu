phonon.options({
    navigator: {
        defaultPage: 'home',
        animatePages: true,
        enableBrowserBackButton: true,
        templateRootDirectory: './tpl'
    },
    i18n: null
});

var app = phonon.navigator();

var sessionService = new SessionService();

app.on({page: 'home', preventClose: false, content: null});

app.on({page: 'message', preventClose: true, content: null, readyDelay: 1});

app.on({page: 'profile', preventClose: true, content: null, readyDelay: 1});

app.on({page: 'sign-out', preventClose: true, content: null, readyDelay: 1});

app.start();