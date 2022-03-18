# pull the official base image
FROM node:13.12.0-alpine

# add app
ADD . /customer-client

# set working direction
WORKDIR /customer-client

RUN npm install

EXPOSE 3000

# start app
CMD ["npm", "run", "start"]
