FROM openjdk:11

COPY ./bin/docker-start.sh /usr/local/bin/docker-start
RUN chmod +x /usr/local/bin/docker-start
CMD /usr/local/bin/docker-start
