docker build -t spring-xy-app .

sudo docker run -p 8080:8080 -t spring-xy-app


# k8s 本地镜像
eval $(minikube docker-env)
minikube image load spring-xy-app 
