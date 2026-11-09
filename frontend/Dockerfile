# Use the official Node.js image as the base image
FROM node:latest


WORKDIR /app
COPY package*.json /app/
RUN npm install
RUN npm install -g @angular/cli@13.3.4-rc.2
COPY ./ /app/
RUN ng build --extract-css --output-path=dist --prod=true


# Run Stage
FROM nginx:1.17.1-alpine
#COPY default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist /usr/share/nginx/html