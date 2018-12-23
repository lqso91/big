### 配置  
git config --system (系统配置)  
git config --global (当前用户配置)
```$xslt
git config --global user.name 'hello'
git config --global user.email 'hello@hello.com'
```

### 查看配置信息
- 查看全部配置
```$xslt
git config --list

（输出有省略）
rebase.autosquash=true
user.name=de-0o0
user.email=lqso91@gmail.com
https.proxy=socks5://127.0.0.1:1080
core.repositoryformatversion=0
core.ignorecase=true
remote.origin.url=git@lqso.top:big
remote.origin.fetch=+refs/heads/*:refs/remotes/origin/*
branch.master.remote=origin
branch.master.merge=refs/heads/master
```
- 查看某项配置
```$xslt
git config user.name
```
### 创建仓库
- 将当前目录作为git仓库
```$xslt
git init
```
- 将指定目录作为git仓库
```$xslt
git init project-a
```
- 从现有仓库克隆
```$xslt
git clone git@lqso.top:testing.git [newname]
```
### 基本操作
![git流程图](git/images/gitwork.png)
- 将文件或目录提交到暂存区
```$xslt
git add file/directory
```

- 查看上次提交之后的改变
```$xslt
git status
git status -s (简短信息)
----------------新增文件未add
On branch master
No commits yet
Untracked files:
  (use "git add <file>..." to include in what will be committed)
        a.txt
nothing added to commit but untracked files present (use "git add" to track)
----------------git add . 后
On branch master
No commits yet
Changes to be committed:
  (use "git rm --cached <file>..." to unstage)
        new file:   a.txt
```
- 将暂存区修改提交到本地库  
git commit仅可提交暂存区内容，所以修改后需要先 git add，再commit
```$xslt
git commit -m '提交日志'
git commit -am '提交日志' （-a, 代替git add）
```
- 比较工作区与暂存区文件差异
```$xslt
git diff [file]
```
- 将本地库中的最新信息发送给远程库
```$xslt
git push
```
- 将文件从暂存区移除
```$xslt
git rm --cached file
```
- 删掉push到远程的文件或文件夹
```$xslt
git rm -r -n --cached .idea (-n 查看要删除的文件)
git rm -r --cached .idea
git commit -m 'remove .idea'
git push
```
### 分支管理
- 列出分支
```$xslt
git branch
```
- 创建分支
```$xslt
git branch branchname
git branch -b branchname (创建并切换分支)
```
- 切换分支
```$xslt
git checkout branchname
```
- 删除分支
```$xslt
git branch -d branchname
```
- 合并分支  
冲突时：手动解决冲突，然后git add，表示冲突已解决
```$xslt
git merge
```
### 查看提交历史  
http://git-scm.com/docs/git-log
```
git log
git log --oneline (简洁输出)
git log --oneline --graph (拓扑图输出)
git log --oneline --reverse (逆向输出)
git log --author=Tom -5 (查看指定作者的提交日志)
git log --oneline --before={3.weeks.ago} --after={2018-12-15}
```
### 标签
- 加标签  
```$xslt
git tag tagname
git tag -a tagname -m '标签描述' 
```
- 查看标签
```$xslt
git tag
```
### 远程仓库
- 添加远程仓库
```$xslt
git remote add <remote> <url>
git remote add origin git@lqso.top:testing
```
- 查看远程仓库
```$xslt
git remote [-v]
```
- 推送到远程仓库
```$xslt
git push [remote] [branch]
git push origin master
```
- 删除远程分支
```$xslt
git remote rm <remote>
```
![git命令](git/images/gitcommand.jpg)