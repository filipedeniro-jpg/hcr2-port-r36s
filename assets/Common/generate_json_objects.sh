#!/bin/bash
RUBE=/Applications/rube-mac32-1.7.4/R.U.B.E.app/Contents/MacOS/rube
FILES=rube/objects/*
for f in $FILES
do
  # take action on each file. $f store current file name
  NAME=`echo "$f" | cut -d'.' -f1`
  NAME="$NAME.json"
  NAME="${NAME/rube/json}"
  echo "Convertin $f file to $NAME"
  $RUBE -i $f -e $NAME
done
