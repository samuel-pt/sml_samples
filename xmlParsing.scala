import scala.xml._
import scala.xml.transform._

object t1 extends RewriteRule {
  override def transform(n: Node): Seq[Node] = n match {
    case Elem(prefix, "Page", attribs, scope, _*)  =>
      Elem(prefix, "Page", attribs, scope, Text("2"))
    case other => other
  }
}

object rt1 extends RuleTransformer(t1)

object t2 extends RewriteRule {
  override def transform(n: Node): Seq[Node] = n match {
    case sn @ Elem(_, "Response_Filter", _, _, _*) => rt1(sn)
    case other => other
  }
}

object rt2 extends RuleTransformer(t2)
