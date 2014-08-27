namespace java com.peony.iview.service

enum LoginType {
    APP,
    QQ,
    WEIBO
}

service IViewService {

    i64 login(1:LoginType loginType, 2:string user, 3:string password),

    oneway void logout()
}