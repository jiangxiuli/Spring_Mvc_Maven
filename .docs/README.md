# Headline

> An awesome project.

# 一级标题

正文

## 二级标题

正文

# GitHub Pages

## 你的主页

 [GitHub Pages官网](https://pages.github.com)里面展示了如何利用GitHub来发布你自己的网站。

1. 在Github中创建你自己的Repository。名字必须是你的Github的名字.github.io。

2. 然后在你的本地Clone下来你新建的项目。

   ```linux
   git clone https://github.com/username/username.github.io
   ```

3. 在你的本地编辑你的项目和加入index.html。

   ```
   cd username.github.io
   echo "Hello World" > index.html
   ```

4. 提交你本地的更改。

   ```
   git add --all
   git commit -m "Initial commit"
   git push -u origin master
   ```

5. 打开你的浏览器，就可以看到你提交的静态文件了。

   ```
   https://username.github.io
   ```

## 组织主页

与个人主页一样的方法。

## 项目文档（Theme）

1. 在GitHub中找到你的项目，进入Settings目录。

2. 下拉到你的GithubPages，选择你想要的模板Theme。

3. 回到你的项目下，加入README.md，并编辑。

4. 现在可以看到你的项目的主页了。

   ```
   https://username.github.io/project_name
   ```

## 项目文档（自己写）

1. 在你的项目目录下，直接编写一个index.html，并提交。

2. 进入你的项目的Settings下。

3. 下拉到GitHubPages，source选择master branch，并save。

4. 现在可以看到你的项目的主页了。

   ```
   https://username.github.io/project_name
   ```

# 编辑文档