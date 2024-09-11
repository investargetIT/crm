# 测试环境部署
1. 使用 Maven 打包生成 jar 文件
2. 删除旧的镜像
2. 构建 Docker 镜像 `docker build -t peidi-wms .`
3. 导出镜像到本地 `docker save -o peidi-wms.tar peidi-wms`
4. 上传镜像到远程服务器
5. 远程服务器上载入镜像 `docker load -i peidi-wms.tar`
6. 停止并移除旧的容器 `docker stop peidi-wms` `docker rm peidi-wms`
7. 运行新的容器 `docker run --name peidi-wms -p 8083:8083 -d peidi-wms`
