#!/bin/sh

git filter-branch --env-filter '
export GIT_AUTHOR_NAME="Danielku15"
export GIT_AUTHOR_EMAIL="danielku15@coderline.net"
export GIT_COMMITTER_NAME="Danielku15"
export GIT_COMMITTER_EMAIL="Danielku15@coderline.net"
'
