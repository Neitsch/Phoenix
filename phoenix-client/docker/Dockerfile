FROM node:argon

WORKDIR /usr/src/app

COPY package.json /usr/src/app/

RUN npm install --silent

COPY . /usr/src/app

EXPOSE 3000

CMD [ "npm", "start" ]
