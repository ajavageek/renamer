# Batch File Renamer

This application allows to rename files in batch using regular expressions.

## Building

```
mvn package
```

## Running

```
docker run -ti -p 8080:8080 -v /Volumes/folder:/Volumes/folder renamer:0.0.1-SNAPSHOT
```

NOTE: Don't forget to mount the volumes of folders you want to rename.