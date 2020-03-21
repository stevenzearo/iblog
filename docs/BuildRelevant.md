### Build Scan (其他task用法相同如gradlew copy --scan)
1. 用于分享项目构建信息(build information)， 可以让他人知道build是否正常等
#### 使用
1. 命令行 ./gradlew build --scan

2. setting.gradle配置
```groovy
plugins {
// https://mvnrepository.com/artifact/org.kordamp.gradle/buildscan-gradle-plugin
    id 'com.gradle.enterprise' version '3.1.1'
}

gradleEnterprise {
    buildScan {
// Uncomment the lines below to agree to the Terms of Service.
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        tag 'IBLOG'
        link 'GitHub', 'https://github.com/gradle/gradle-build-scan-quickstart'
    }
}
```
配置完成执行gradlew build --scan后，会提示build信息所在页面url，之后（首次执行build --scan）需要邮箱验证

### Define A Task
```groovy
task copy(type: Copy, group: 'CustomerTest', description: 'copy sources to dest directory') {
    from rootDir.getAbsolutePath() + "/gradle"
    into './dest/gradle'
}
```

### Discover available properties
1. gradlew properties查看可用的properties