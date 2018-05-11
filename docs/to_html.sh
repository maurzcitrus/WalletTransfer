my_files=*.md
for f in $my_files
do
	from=$f 
	to="${f%.*}.html"
	echo "Converting from $f to $to"
	`pandoc -s -S -c ./css/base.css -c ./css/superfish-navbar-smoothness.css -c ./css/superfish-smoothness.css -B header.html -B menu.html -A footer.html -T "Team stateless - Enterprise Java CA 1" $from -o $to`
done