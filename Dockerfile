ARG DIGEST=sha256:e04922091c37c716e0f743a482c35b87ef344aa08fd111ebaa60c6f8dc89777b
FROM yogihardi/alpine-scala-maven@${DIGEST}

ARG APP_DIR=/usr/src/app
WORKDIR $APP_DIR
