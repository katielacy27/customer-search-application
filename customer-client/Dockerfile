# pull the official base image
FROM node:13.12.0-alpine

# set working direction
WORKDIR /app

# install application dependencies
COPY customer-client/package.json ./
COPY customer-client/package-lock.json ./
RUN npm i

# add app
COPY . ./

# start app
CMD ["npm", "run", "start"]
