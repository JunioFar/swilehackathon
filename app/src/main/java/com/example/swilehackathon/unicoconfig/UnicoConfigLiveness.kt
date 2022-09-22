package com.example.swilehackathon.unicoconfig

import com.acesso.acessobio_android.onboarding.AcessoBioConfigDataSource

class UnicoConfigLiveness : AcessoBioConfigDataSource {
    override fun getBundleIdentifier(): String {
        return "com.example.swilehackathon"
    }

    override fun getHostInfo(): String {
        return "nRMqSJJeWMZ0K4n9Dxs/Zhb5RslAxes+pmH0gJgmVtbVpfWJCuvOEjv4BlQmmXyO"
    }

    override fun getHostKey(): String {
        return "38SF83e0Fgx3M88gs+D6vVgnMWy6C0HNN2qTBze1dzm62A9f/qYQCkV8QqksAAi6"
    }

    override fun getMobileSdkAppId(): String {
        return "1:36896:android"
    }

    override fun getProjectId(): String {
        return "SwileHackathon"
    }

    override fun getProjectNumber(): String {
        return "4596801484928279023315"
    }
}