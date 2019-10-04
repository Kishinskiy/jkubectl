# jkubectl

JKubectl Library from kubernetes Api

#### Usage:

Call method getPods() for get pods list from default namespace
```Java
Kubectl kubectl = new Kubectl();
kubectl.getPods(); // get pods list from default name space
kubectl.getPods("Exapmle"); // get pods list from namespace "Example"
```
