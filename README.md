Synopsis
--------
The EMF fragments framework is an Eclipse Modeling Framework (EMF) persistence layer. It is designed to store large object-oriented data models (typed, labeled, bidirectional graphs). EMF fragments emphasize on fast storage of new data and fast navigation of persisted models. The requirements for this framework come from storing and analyzing large ammounts of sensor data in real-time. 

EMF fragments are different to frameworks based on an object relatational mappings (ORM) like Connected Data Objects (CDO). While ORM mappings map single objects, attributes, and references to databae entries, EMF fragments map larger chunks of a model (fragments) to URIs. This allows to store models on a wide range of distributed data-stores inlcuding distributed file-systems and key-value stores (think NoSQL databases like MongoDB or HBase). EMF fragments therefore provides storage for typed structured data that allows analyzes based on the map-reduce or bulk synchronous parallel (BSP) paradigm (i.e. for cloud computing).

The EMF fragments framework allows automated transparent background framgmentation of models. Clients designate types of references at which models are fragmented. This allows to control fragmentation without the need to trigger it manually. Fragments are managed automatically: when you create, delete, move, edit model elements new fragments are created and elements are distributed among those fragments on the fly. Fragments (i.e. resources) are identified by URIs. The framework allows to map URIs to (distributed) data-stores (e.g. NoSql databases or distributed file systems).

Contents
--------
* The `de.hub.emffrag` is the main eclipse project that contains everything necessary to use EMFFrag. 
* The `de.hub.emffrag.hbase` project contains a mapping into HBase tables as datastore backend.
* The `de.hub.emffrag.mongodb` provides a mapping onto Mongodb.
* All other projects contain tests, testmodels, benchmarks, and an simple HelloWorld example.

Getting Started
---------------
Look at this [HelloWorldExample](https://github.com/markus1978/emf-fragments/blob/master/de.hub.emffrag.tests/src/de/hub/emffrag/example/HelloWorldExample.java), or have a look at the [wiki](https://github.com/markus1978/emf-fragments/wiki).

License
-------
EMFFrag and all related projects are published under the Apache License 2.0.

Copyright 2012 Markus Scheidgen
