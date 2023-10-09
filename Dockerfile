FROM ghcr.io/graalvm/graalvm-ce:ol8-java17

ADD build/distributions/blog-0.0.1.tar /
WORKDIR /blog-0.0.1

CMD ["bin/blog"]
